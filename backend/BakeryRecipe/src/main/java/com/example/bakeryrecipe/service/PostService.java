package com.example.bakeryrecipe.service;


import com.example.bakeryrecipe.authentication.UserDetailsImpl;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.repository.PostRepository;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class PostService implements BaseService<PostDTO> {

    private final PostMapper mapper;
    private final RecipeService recipeService;
    private final PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PostService(PostMapper mapper, RecipeService recipeService, PostRepository postRepository) {
        this.mapper = mapper;
        this.recipeService = recipeService;
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        if (isNull(dto.getId())) {
            Post entity = mapper.toEntity(dto);
            entity = postRepository.save(entity);
            RecipeDTO recipeDTO = nonNull(dto.getRecipe()) ? recipeService.save(entity, dto.getRecipe()) : null;

            dto = mapper.toDTO(entity);
            dto.setRecipe(recipeDTO);
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.CREATED, "This post could be created");
        }
    }

    @Override
    public PostDTO update(PostDTO dto) {
        Post entity;
        Post oldEntity = postRepository.findPostsById(dto.getId());
        if (nonNull(oldEntity)) {
            entity = mapper.toEntity(dto, oldEntity);
            entity = postRepository.save(entity);
            return mapper.toDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
        }
    }



    public Page<PostDTO> findAll(Pageable pageable) {

        entityManager.unwrap(Session.class).enableFilter("likeFilter");

        Page<Post> entities = postRepository.findAll(pageable);

        Page<PostDTO> postDTOS = new PageImpl<>(mapper.toDTOList(entities.getContent()),pageable,entities.getTotalElements());

        return postDTOS;
    }

    public Page<PostDTO> findAllByMemberId(long id, Pageable pageable) {
       Page<Post> list = postRepository.findAllByMemberId(id, pageable);
       Page<PostDTO> postDTOS = new PageImpl<>(mapper.toDTOList(list.getContent()),pageable,list.getTotalElements());
       return postDTOS;
    }

    public PostDTO delete(long id) {
        Post entity = postRepository.findPostsById(id);
        if(entity != null){
            UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (entity.getMember().getId().equals(userDetails.getId()) && userDetails.getAuthorities().contains("ADMIN")) {
                postRepository.delete(entity);
                return mapper.toDTO(entity);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not post owner");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this post");
        }
    }

    public PostDTO search(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
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
