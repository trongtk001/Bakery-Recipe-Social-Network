package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RecipeMapper implements Mapper<Recipe, RecipeDTO> {
    private final ModelMapper modelMapper;

    public RecipeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Recipe toEntity(RecipeDTO dto) {
        return modelMapper.map(dto, Recipe.class);
    }

    @Override
    public Recipe toEntity(RecipeDTO dto, Recipe entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public RecipeDTO toDTO(Recipe entity) {
        return modelMapper.map(entity, RecipeDTO.class);
    }

    @Override
    public RecipeDTO toDTO(Recipe entity, RecipeDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<Recipe> toEntityList(List<RecipeDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<RecipeDTO> toDTOList(List<Recipe> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
