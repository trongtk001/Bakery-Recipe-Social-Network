package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.RecipeIngredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {
    public IngredientMapper(ModelMapper modelMapper) {
        super(modelMapper, Ingredient.class, IngredientDTO.class);
    }


    public static PropertyMap<RecipeIngredient, IngredientDTO> entityToDTOPropertyMap = new PropertyMap<RecipeIngredient, IngredientDTO>() {
        @Override
        protected void configure() {
            map(source.getIngredients().getId(), destination.getId());
            map(source.getIngredients().getIngredients(), destination.getIngredients());
            map(source.getIngredients().getDescription(), destination.getDescription());
            map(source.getIngredients().getUnit(), destination.getUnit());
            map(source.getQuantity(), destination.getQuantity());
        }
    };

    public static PropertyMap<IngredientDTO, RecipeIngredient> dtoToEntityPropertyMap = new PropertyMap<IngredientDTO, RecipeIngredient>() {
        @Override
        protected void configure() {
            map(source.getId(), destination.getIngredients().getId());
            map(source.getIngredients(), destination.getIngredients().getIngredients());
            map(source.getDescription(), destination.getIngredients().getDescription());
            map(source.getUnit(), destination.getIngredients().getUnit());
            map(source.getQuantity(), destination.getQuantity());
        }
    };
}
