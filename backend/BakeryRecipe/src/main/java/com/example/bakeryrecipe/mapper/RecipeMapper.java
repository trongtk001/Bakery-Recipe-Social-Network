package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class RecipeMapper extends BaseMapper<Recipe, RecipeDTO> {

    public RecipeMapper(ModelMapper modelMapper) {
        super(modelMapper, Recipe.class, RecipeDTO.class);
    }

}
