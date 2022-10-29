package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper extends BaseMapper<Post, PostDTO>{

    public PostMapper(ModelMapper modelMapper) {
        super(modelMapper, Post.class, PostDTO.class);
    }
}
