package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {
    public IngredientMapper(ModelMapper modelMapper) {
        super(modelMapper, Ingredient.class, IngredientDTO.class);
    }
}
