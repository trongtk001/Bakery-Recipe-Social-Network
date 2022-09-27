package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.PostDTO;

import java.util.List;

public interface PostService extends Service<PostDTO>{
    List<PostDTO> findAll();
}
