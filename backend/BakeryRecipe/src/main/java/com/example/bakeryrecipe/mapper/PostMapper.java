package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper implements Mapper<Post, PostDTO>{

    private final ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Post toEntity(PostDTO dto) {
        return modelMapper.map(dto, Post.class);
    }

    @Override
    public Post toEntity(PostDTO dto, Post entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public PostDTO toDTO(Post entity) {
        return modelMapper.map(entity, PostDTO.class);
    }

    @Override
    public PostDTO toDTO(Post entity, PostDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<Post> toEntityList(List<PostDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> toDTOList(List<Post> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
