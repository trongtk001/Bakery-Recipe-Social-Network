package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.StepIngredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper extends BaseMapper<Ingredient, IngredientDTO> {
    public IngredientMapper(ModelMapper modelMapper) {
        super(modelMapper, Ingredient.class, IngredientDTO.class);
    }

    public static PropertyMap<StepIngredient, IngredientDTO> entityToDTOPropertyMap = new PropertyMap<StepIngredient, IngredientDTO>() {
        @Override
        protected void configure() {
            map(source.getIngredients().getId(), destination.getId());
            map(source.getIngredients().getIngredients(), destination.getIngredients());
            map(source.getIngredients().getDescription(), destination.getDescription());
            map(source.getIngredients().getUnit(), destination.getUnit());
        }
    };

    public static PropertyMap<IngredientDTO, StepIngredient> dtoToEntityPropertyMap = new PropertyMap<IngredientDTO, StepIngredient>() {
        @Override
        protected void configure() {
        }
    };
}
