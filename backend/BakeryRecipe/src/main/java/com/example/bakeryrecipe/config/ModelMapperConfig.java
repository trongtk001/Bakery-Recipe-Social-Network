package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.dto.*;
import com.example.bakeryrecipe.entity.*;
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
        modelMapper.addMappings(RecipePropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(RecipePropertyMap.dtoToEntityPropertyMap);
        modelMapper.addMappings(MemberPropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(MemberPropertyMap.dtoToEntityPropertyMap);
        modelMapper.addMappings(IngredientPropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(CommentPropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(EmojiPropertyMap.entityToDTOPropertyMap);
        return modelMapper;
    }
}

class RecipePropertyMap {
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

class MemberPropertyMap {
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

class IngredientPropertyMap {
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

class CommentPropertyMap {
    static PropertyMap<Comment, CommentDTO> entityToDTOPropertyMap = new PropertyMap<Comment, CommentDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPost());
        }
    };
}

class EmojiPropertyMap {
    static PropertyMap<Emoji, EmojiDTO> entityToDTOPropertyMap = new PropertyMap<Emoji, EmojiDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPost());
        }
    };
}