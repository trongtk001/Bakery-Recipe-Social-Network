package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.entity.Follow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper extends BaseMapper<Follow, FollowDTO>{
    public FollowMapper(ModelMapper modelMapper) {
        super(modelMapper, Follow.class, FollowDTO.class);
    }
}
