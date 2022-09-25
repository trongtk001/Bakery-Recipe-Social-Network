package com.example.bakeryrecipe.service.impl;


import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.repository.PostRepository;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper mapper;
    private final PostRepository postRepository;


    public PostServiceImpl(PostMapper mapper, PostRepository postRepository) {
        this.mapper = mapper;
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        Post entity;
        if(dto.getId() == null){
            entity = mapper.toEntity(dto);
            postRepository.save(entity);
            return mapper.toDTO(entity);
        }
        return null;
    }

    @Override
    public PostDTO edit(PostDTO dto) {
        Post oldPost = postRepository.findPostByMemberId(dto.getId());
        Post entity = mapper.toEntity(dto,oldPost);
        postRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public PostDTO delete(long id) {
        return null;
    }

    @Override
    public PostDTO search(Long id) {
        return null;
    }
}
