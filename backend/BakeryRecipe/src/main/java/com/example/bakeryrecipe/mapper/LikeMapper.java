package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.entity.Like;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper extends BaseMapper<Like, LikeDTO>{
    public LikeMapper(ModelMapper modelMapper) {
        super(modelMapper, Like.class, LikeDTO.class);
    }
}
