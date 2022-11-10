package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
@Component
public class RecipeMapper extends BaseMapper<Recipe, RecipeDTO> {

    public RecipeMapper(ModelMapper modelMapper) {
        super(modelMapper, Recipe.class, RecipeDTO.class);
    }

    public static PropertyMap<Recipe, RecipeDTO> entityToDTOPropertyMap = new PropertyMap<Recipe, RecipeDTO>() {
        @Override
        protected void configure() {
            map(source.toolToList(), destination.getTool());
        }
    };

    public static PropertyMap<RecipeDTO, Recipe> dtoToEntityPropertyMap = new PropertyMap<RecipeDTO, Recipe>() {
        @Override
        protected void configure() {
            map(source.toolToString(), destination.getTool());
            skip(destination.getSteps());
        }
    };

}
