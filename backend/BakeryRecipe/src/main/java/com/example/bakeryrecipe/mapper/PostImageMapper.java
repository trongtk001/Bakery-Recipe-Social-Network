package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.PostImage;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostImageMapper implements Mapper<PostImage, PostImageDTO>{
    private final ModelMapper modelMapper;

    public PostImageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostImage toEntity(PostImageDTO dto) {
        return modelMapper.map(dto, PostImage.class);
    }

    @Override
    public PostImage toEntity(PostImageDTO dto, PostImage entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public PostImageDTO toDTO(PostImage entity) {
        return modelMapper.map(entity, PostImageDTO.class);
    }

    @Override
    public PostImageDTO toDTO(PostImage entity, PostImageDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<PostImage> toEntityList(List<PostImageDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<PostImageDTO> toDTOList(List<PostImage> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
