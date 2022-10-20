package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.RecipeIngredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(recipePropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(recipePropertyMap.dtoToEntityPropertyMap);
        modelMapper.addMappings(memberPropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(memberPropertyMap.dtoToEntityPropertyMap);
        modelMapper.addMappings(ingredientPropertyMap.entityToDTOPropertyMap);
        return modelMapper;
    }
}

class recipePropertyMap {
    static PropertyMap<Recipe, RecipeDTO> entityToDTOPropertyMap = new PropertyMap<Recipe, RecipeDTO>() {
        @Override
        protected void configure() {
            map(source.stepsToList(), destination.getSteps());
            map(source.toolToList(), destination.getTool());
        }
    };

    static PropertyMap<RecipeDTO, Recipe> dtoToEntityPropertyMap = new PropertyMap<RecipeDTO, Recipe>() {
        @Override
        protected void configure() {
            map(source.stepsToString(), destination.getSteps());
            map(source.toolToString(), destination.getTool());
        }
    };
}

class memberPropertyMap {
    static PropertyMap<Member, MemberDTO> entityToDTOPropertyMap = new PropertyMap<Member, MemberDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };

    static PropertyMap<MemberDTO, Member> dtoToEntityPropertyMap = new PropertyMap<MemberDTO, Member>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };
}

class ingredientPropertyMap {
    static PropertyMap<RecipeIngredient, IngredientDTO> entityToDTOPropertyMap = new PropertyMap<RecipeIngredient, IngredientDTO>() {
        @Override
        protected void configure() {
            map(source.getIngredients().getId(), destination.getId());
            map(source.getIngredients().getIngredients(), destination.getIngredients());
            map(source.getIngredients().getDescription(), destination.getDescription());
            map(source.getIngredients().getUnit(), destination.getUnit());
        }
    };

    static PropertyMap<IngredientDTO, RecipeIngredient> dtoToEntityPropertyMap = new PropertyMap<IngredientDTO, RecipeIngredient>() {
        @Override
        protected void configure() {
        }
    };
}