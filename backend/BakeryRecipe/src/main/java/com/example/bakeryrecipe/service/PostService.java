package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;

import java.util.List;

public interface PostService extends Service<PostDTO>{
    List<PostDTO> findAll();
    List<PostDTO> findAllByMemberId(long id);
}
