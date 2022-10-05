package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.PostImageDTO;
import com.example.bakeryrecipe.dto.PostVideoDTO;
import com.example.bakeryrecipe.entity.PostImage;
import com.example.bakeryrecipe.entity.PostVideo;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostVideoMapper implements Mapper<PostVideo, PostVideoDTO>{
    private final ModelMapper modelMapper;

    public PostVideoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostVideo toEntity(PostVideoDTO dto) {
        return modelMapper.map(dto, PostVideo.class);
    }

    @Override
    public PostVideo toEntity(PostVideoDTO dto, PostVideo entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public PostVideoDTO toDTO(PostVideo entity) {
        return modelMapper.map(entity, PostVideoDTO.class);
    }

    @Override
    public PostVideoDTO toDTO(PostVideo entity, PostVideoDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<PostVideo> toEntityList(List<PostVideoDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<PostVideoDTO> toDTOList(List<PostVideo> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
