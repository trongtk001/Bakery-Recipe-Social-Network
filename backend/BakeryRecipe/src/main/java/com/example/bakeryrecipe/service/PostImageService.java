package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.entity.*;
import com.example.bakeryrecipe.mapper.PostImageMapper;
import com.example.bakeryrecipe.repository.PostImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostImageService implements BaseService<PostImageDTO>{

    private final PostImageRepository postImageRepository;
    private final PostImageMapper mapper;

    public PostImageService(PostImageRepository postImageRepository, PostImageMapper mapper) {
        this.postImageRepository = postImageRepository;
        this.mapper = mapper;
    }

    public List<PostImageDTO> saves(Post post){
        return post.getPostImages().stream().map(postImage -> {
            postImage.setPost(post);
            return mapper.toDTO(postImageRepository.save(postImage));
        }).collect(Collectors.toList());
    }
    @Override
    public PostImageDTO save(PostImageDTO dto) {
        return null;
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
