package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.entity.Comment;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper implements Mapper<Comment, CommentDTO>{

    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment toEntity(CommentDTO dto) {
        return modelMapper.map(dto, Comment.class);
    }

    @Override
    public Comment toEntity(CommentDTO dto, Comment entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public CommentDTO toDTO(Comment entity) {
        return modelMapper.map(entity, CommentDTO.class);
    }

    @Override
    public CommentDTO toDTO(Comment entity, CommentDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;

    }

    @Override
    public List<Comment> toEntityList(List<CommentDTO> dtos) {
        return dtos.stream().map(commentDTO -> toEntity(commentDTO)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> toDTOList(List<Comment> entities) {
        return entities.stream().map(comment -> toDTO(comment)).collect(Collectors.toList());
    }
}
