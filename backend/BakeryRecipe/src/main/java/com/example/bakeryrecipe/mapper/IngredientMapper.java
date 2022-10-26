package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {
    public IngredientMapper(ModelMapper modelMapper) {
        super(modelMapper, Ingredient.class, IngredientDTO.class);
    }
}
