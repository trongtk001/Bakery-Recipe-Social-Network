package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.entity.*;
import com.example.bakeryrecipe.mapper.PostImageMapper;
import com.example.bakeryrecipe.repository.PostImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostImageService implements BaseService<PostImageDTO>{

    private final PostImageRepository postImageRepository;
    private final PostImageMapper mapper;

    public PostImageService(PostImageRepository postImageRepository, PostImageMapper mapper) {
        this.postImageRepository = postImageRepository;
        this.mapper = mapper;
    }

    @Override
    public PostImageDTO save(PostImageDTO dto) {
        PostImage postImage = mapper.toEntity(dto);
        postImage = postImageRepository.save(postImage);
        return mapper.toDTO(postImage);
    }

    @Override
    public PostImageDTO delete(long id) {
        return null;
    }

    @Override
    public PostImageDTO search(Long id) {
        return null;
    }
}
