package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.entity.Like;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.StepIngredient;
import com.example.bakeryrecipe.mapper.CommentMapper;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.mapper.LikeMapper;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.mapper.MessageMapper;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.mapper.SharePostMapper;
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

        modelMapper.addMappings(RecipeMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(RecipeMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(MemberMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(MemberMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(IngredientMapper.entityToDTOPropertyMap);

        modelMapper.addMappings(CommentMapper.entityToDTOPropertyMap);

        modelMapper.addMappings(LikeMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(LikeMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(FollowMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(FollowMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(PostMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(MessageMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(MessageMapper.dtoToEntityPropertyMap);

        modelMapper.addMappings(SharePostMapper.entityToDTOPropertyMap);
        modelMapper.addMappings(SharePostMapper.dtoToEntityPropertyMap);

        return modelMapper;
    }
}