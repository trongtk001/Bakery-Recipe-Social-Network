package com.example.bakeryrecipe.service;


import com.example.bakeryrecipe.authentication.UserDetailsImpl;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.dto.PostVideoDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.repository.PostImageRepository;
import com.example.bakeryrecipe.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService implements BaseService<PostDTO> {

    private final PostMapper mapper;
    private final RecipeService recipeService;
    private final PostRepository postRepository;
    private final PostImageService postImageService;
    private final PostVideoService postVideoService;

    public PostService(PostMapper mapper, RecipeService recipeService, PostRepository postRepository, PostImageService postImageService, PostVideoService postVideoService) {
        this.mapper = mapper;
        this.recipeService = recipeService;
        this.postRepository = postRepository;
        this.postImageService = postImageService;
        this.postVideoService = postVideoService;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        Post entity = mapper.toEntity(dto);
        Recipe recipe = entity.getRecipe();
        entity.setRecipe(null);

        entity = postRepository.save(entity);

        List<PostImageDTO> postImageDTOS = postImageService.saves(entity);
        List<PostVideoDTO> postVideoDTOS = postVideoService.saves(entity);
        RecipeDTO recipeDTO = recipeService.save(entity, dto.getRecipe());

        PostDTO postDTO = mapper.toDTO(entity);
        postDTO.setPostImages(postImageDTOS);
        postDTO.setPostVideos(postVideoDTOS);
        postDTO.setRecipe(recipeDTO);
        return postDTO;
    }

    public PostDTO update(PostDTO dto) {
        Post entity;
        Post oldEntity = postRepository.findPostsById(dto.getId());
        if (oldEntity != null) {
            entity = mapper.toEntity(dto, oldEntity);
            entity = postRepository.save(entity);
            return mapper.toDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
        }
    }

    public Page<PostDTO> findAll(Pageable pageable) {
        Page<Post> entities = postRepository.findAll(pageable);
        Page<PostDTO> postDTOS = new PageImpl<>(mapper.toDTOList(entities.getContent()),pageable,entities.getTotalElements());
        return postDTOS;
    }

    public Page<PostDTO> findAllByMemberId(long id, Pageable pageable) {
       Page<Post> list = postRepository.findAllByMemberId(id, pageable);
       Page<PostDTO> postDTOS = new PageImpl<>(mapper.toDTOList(list.getContent()),pageable,list.getTotalElements());
       return postDTOS;
    }

    @Override
    public PostDTO delete(long id) {
        Post entity = postRepository.findPostsById(id);
        if(entity != null){
            UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (entity.getMember().getId().equals(userDetails.getId())) {
                postRepository.delete(entity);
                return mapper.toDTO(entity);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not post owner");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this post");
        }
    }

    @Override
    public PostDTO search(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
        }
        return mapper.toDTO(post);
    }

    public Post searchEntity(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Page<PostDTO> searchPostByName(String q, Pageable pageable){
        Page<Post> posts = postRepository.findAllByPostBodyOrMember_Name(q, pageable);
        if(posts != null){
            return new PageImpl<>(mapper.toDTOList(posts.getContent()), pageable, posts.getTotalElements());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found");
    }
}
