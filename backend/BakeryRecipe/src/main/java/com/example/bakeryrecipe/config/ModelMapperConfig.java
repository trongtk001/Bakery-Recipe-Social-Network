package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.mapper.CommentMapper;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.mapper.LikeMapper;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.mapper.MessageMapper;
import com.example.bakeryrecipe.mapper.PostMapper;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.mapper.SharePostMapper;
import com.example.bakeryrecipe.mapper.ToolMapper;
import org.modelmapper.ModelMapper;
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

        modelMapper.addMappings(IngredientMapper.dtoToEntityPropertyMap);
        modelMapper.addMappings(IngredientMapper.entityToDTOPropertyMap);

        modelMapper.addMappings(ToolMapper.dtoToEntityPropertyMap);
        modelMapper.addMappings(ToolMapper.entityToDTOPropertyMap);

        return modelMapper;
    }
}