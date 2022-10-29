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

        modelMapper.addMappings(LikePropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(LikePropertyMap.dtoToEntityPropertyMap);

        modelMapper.addMappings(FollowPropertyMap.entityToDTOPropertyMap);
        modelMapper.addMappings(FollowPropertyMap.dtoToEntityPropertyMap);

        modelMapper.addMappings(PostPropertyMap.dtoToEntityPropertyMap);
        return modelMapper;
    }
}

class RecipePropertyMap {
    static PropertyMap<Recipe, RecipeDTO> entityToDTOPropertyMap = new PropertyMap<Recipe, RecipeDTO>() {
        @Override
        protected void configure() {
            map(source.toolToList(), destination.getTool());
        }
    };

    static PropertyMap<RecipeDTO, Recipe> dtoToEntityPropertyMap = new PropertyMap<RecipeDTO, Recipe>() {
        @Override
        protected void configure() {
            map(source.toolToString(), destination.getTool());
            skip(destination.getSteps());
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
    static PropertyMap<StepIngredient, IngredientDTO> entityToDTOPropertyMap = new PropertyMap<StepIngredient, IngredientDTO>() {
        @Override
        protected void configure() {
            map(source.getIngredients().getId(), destination.getId());
            map(source.getIngredients().getIngredients(), destination.getIngredients());
            map(source.getIngredients().getDescription(), destination.getDescription());
            map(source.getIngredients().getUnit(), destination.getUnit());
        }
    };

    static PropertyMap<IngredientDTO, StepIngredient> dtoToEntityPropertyMap = new PropertyMap<IngredientDTO, StepIngredient>() {
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

class LikePropertyMap {
    static PropertyMap<Like, LikeDTO> entityToDTOPropertyMap = new PropertyMap<Like, LikeDTO>() {
        @Override
        protected void configure() {
            map(source.getMember().getId(), destination.getMemberID());
            map(source.getPost().getId(), destination.getPostID());
        }
    };

    static PropertyMap<LikeDTO, Like> dtoToEntityPropertyMap = new PropertyMap<LikeDTO, Like>() {
        @Override
        protected void configure() {
            map(source.getMemberID(), destination.getMember().getId());
            map(source.getPostID(), destination.getPost().getId());
        }
    };
}

class FollowPropertyMap {
    static PropertyMap<Follow, FollowDTO> entityToDTOPropertyMap = new PropertyMap<Follow, FollowDTO>() {
        @Override
        protected void configure() {
            map(source.getMember().getId(), destination.getMemberID());
            map(source.getFollower().getId(), destination.getFollowerID());
        }
    };

    static PropertyMap<FollowDTO, Follow> dtoToEntityPropertyMap = new PropertyMap<FollowDTO, Follow>() {
        @Override
        protected void configure() {
            map(source.getMemberID(), destination.getMember().getId());
            map(source.getFollowerID(), destination.getFollower().getId());
        }
    };
}

class PostPropertyMap {
    static PropertyMap<PostDTO, Post> dtoToEntityPropertyMap = new PropertyMap<PostDTO, Post>() {
        @Override
        protected void configure() {
            skip(destination.getRecipe());
        }
    };
}