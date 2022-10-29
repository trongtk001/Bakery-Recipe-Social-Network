package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.entity.Like;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LikeMapper extends BaseMapper<Like, LikeDTO>{
    public LikeMapper(ModelMapper modelMapper) {
        super(modelMapper, List.class, LikeDTO.class);
    }
}
