package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper implements Mapper<Ingredient, IngredientDTO> {

    private final ModelMapper modelMapper;

    public IngredientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Ingredient toEntity(IngredientDTO dto) {
        return modelMapper.map(dto, Ingredient.class);
    }

    @Override
    public Ingredient toEntity(IngredientDTO dto, Ingredient entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public IngredientDTO toDTO(Ingredient entity) {
        return modelMapper.map(entity, IngredientDTO.class);
    }

    @Override
    public IngredientDTO toDTO(Ingredient entity, IngredientDTO dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public List<Ingredient> toEntityList(List<IngredientDTO> dtos) {
        return dtos.stream().map(ingredientDTO -> toEntity(ingredientDTO)).collect(Collectors.toList());
    }

    @Override
    public List<IngredientDTO> toDTOList(List<Ingredient> entities) {
        return entities.stream().map(ingredient -> toDTO(ingredient)).collect(Collectors.toList());
    }
}
