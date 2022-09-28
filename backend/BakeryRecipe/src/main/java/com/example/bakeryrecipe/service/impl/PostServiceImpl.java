package com.example.bakeryrecipe.service.impl;


import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.repository.MemberRepository;
import com.example.bakeryrecipe.repository.PostRepository;
import com.example.bakeryrecipe.service.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Post entity = mapper.toEntity(dto);
        entity = postRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public PostDTO update(PostDTO dto) {
        Post entity;
        Post oldEntity = postRepository.findPostsById(dto.getId());
            entity = mapper.toEntity(dto, oldEntity);
            entity = postRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> entities = postRepository.findAll();
        return mapper.toDTOList(entities);
    }

    @Override
    public List<PostDTO> findAllByMemberId(long id) {
       List<Post> list = postRepository.findAllByMemberId(id);
        return mapper.toDTOList(list);
    }

    @Override
    public PostDTO delete(long id) {
        Post entity = postRepository.findPostsById(id);
        if(entity != null){
            postRepository.delete(entity);
            return mapper.toDTO(entity);
        }
       return null;
    }

    @Override
    public PostDTO search(Long id) {
        return null;
    }
}
