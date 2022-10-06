package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.dto.PostVideoDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.mapper.PostVideoMapper;
import com.example.bakeryrecipe.repository.PostVideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostVideoService implements BaseService<PostVideoService>{
    private final PostVideoMapper mapper;
    private final PostVideoRepository postVideoRepository;

    public PostVideoService(PostVideoMapper mapper, PostVideoRepository postVideoRepository) {
        this.mapper = mapper;
        this.postVideoRepository = postVideoRepository;
    }

    public List<PostVideoDTO> saves(Post post){
        return post.getPostVideos().stream().map(postVideo -> {
            postVideo.setPost(post);
            return mapper.toDTO(postVideoRepository.save(postVideo));
        }).collect(Collectors.toList());
    }
    @Override
    public PostVideoService save(PostVideoService dto) {
        return null;
    }

    @Override
    public PostVideoService delete(long id) {
        return null;
    }

    @Override
    public PostVideoService search(Long id) {
        return null;
    }
}
