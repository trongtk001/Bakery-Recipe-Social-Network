package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.entity.Follow;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FollowMapper implements Mapper<Follow, FollowDTO>{
    private final ModelMapper modelMapper;

    public FollowMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Follow toEntity(FollowDTO dto) {
        return modelMapper.map(dto, Follow.class);
    }

    @Override
    public Follow toEntity(FollowDTO dto, Follow entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public FollowDTO toDTO(Follow entity) {
        return modelMapper.map(entity, FollowDTO.class);
    }

    @Override
    public FollowDTO toDTO(Follow entity, FollowDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<Follow> toEntityList(List<FollowDTO> dtos) {
        return dtos.stream().map(commentDTO -> toEntity(commentDTO)).collect(Collectors.toList());
    }

    @Override
    public List<FollowDTO> toDTOList(List<Follow> entities) {
        return entities.stream().map(comment -> toDTO(comment)).collect(Collectors.toList());
    }
}
