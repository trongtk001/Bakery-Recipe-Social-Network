package com.example.bakeryrecipe.service;


import com.example.bakeryrecipe.authentication.UserDetailsImpl;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.PostImage;
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
    private final PostImageRepository postImageRepository;
    private final PostVideoService postVideoService;

    public PostService(PostMapper mapper, RecipeService recipeService, PostRepository postRepository, PostImageService postImageService, PostImageRepository postImageRepository, PostVideoService postVideoService) {
        this.mapper = mapper;
        this.recipeService = recipeService;
        this.postRepository = postRepository;
        this.postImageService = postImageService;
        this.postImageRepository = postImageRepository;
        this.postVideoService = postVideoService;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        Post entity = mapper.toEntity(dto);

        Recipe recipe = entity.getRecipe();
        entity.setRecipe(null);

        entity = postRepository.save(entity);

        PostDTO postDTO = mapper.toDTO(entity);
        postDTO.setPostImages(postImageService.saves(entity));
        postDTO.setPostVideos(postVideoService.saves(entity));
        postDTO.setRecipe(recipeService.saves(entity, recipe));
        return postDTO;
    }

    public PostDTO update(PostDTO dto) {
        Post entity;
        Post oldEntity = postRepository.findPostsById(dto.getId());
            entity = mapper.toEntity(dto, oldEntity);
            entity = postRepository.save(entity);
        return mapper.toDTO(entity);
    }

    public Page<PostDTO> findAll(Pageable pageable) {
        Page<Post> entities = postRepository.findAll(pageable);
        Page<PostDTO> postDTOS = new PageImpl<>(mapper.toDTOList(entities.getContent()),pageable,entities.getTotalElements());
        return postDTOS;
    }

    public List<PostDTO> findAllByMemberId(long id) {
       List<Post> list = postRepository.findAllByMemberId(id);
        return mapper.toDTOList(list);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found post");
        }
    }

    @Override
    public PostDTO search(Long id) {
        return null;
    }

    public Post searchEntity(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Page<PostDTO> searchPostByName(String q, Pageable pageable){
        Page<Post> posts = postRepository.findAllByPostBodyOrMember_Name(q, pageable);
        if(posts != null){
            return new PageImpl<>(mapper.toDTOList(posts.getContent()), pageable, posts.getTotalElements());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");
    }
}
