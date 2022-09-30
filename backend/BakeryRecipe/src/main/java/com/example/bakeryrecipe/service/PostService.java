package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostDTO;

import java.util.List;

public interface PostService extends Service<PostDTO>{
    PostDTO update(PostDTO postDTO);
    List<PostDTO> findAll();
    List<PostDTO> findAllByMemberId(long id);
    List<PostDTO> searchPostByName(String name);
}
