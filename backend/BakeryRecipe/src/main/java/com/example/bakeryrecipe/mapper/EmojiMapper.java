package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.EmojiDTO;
import com.example.bakeryrecipe.entity.Emoji;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmojiMapper implements Mapper<Emoji, EmojiDTO>{
    private final ModelMapper modelMapper;

    public EmojiMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Emoji toEntity(EmojiDTO dto) {
        return modelMapper.map(dto, Emoji.class);
    }

    @Override
    public Emoji toEntity(EmojiDTO dto, Emoji entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public EmojiDTO toDTO(Emoji entity) {
        return modelMapper.map(entity, EmojiDTO.class);
    }

    @Override
    public EmojiDTO toDTO(Emoji entity, EmojiDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;

    }

    @Override
    public List<Emoji> toEntityList(List<EmojiDTO> dtos) {
        return dtos.stream().map(commentDTO -> toEntity(commentDTO)).collect(Collectors.toList());
    }

    @Override
    public List<EmojiDTO> toDTOList(List<Emoji> entities) {
        return entities.stream().map(comment -> toDTO(comment)).collect(Collectors.toList());
    }
}
