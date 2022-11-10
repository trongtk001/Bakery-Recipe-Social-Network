package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.entity.Like;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper extends BaseMapper<Like, LikeDTO>{
    public LikeMapper(ModelMapper modelMapper) {
        super(modelMapper, Like.class, LikeDTO.class);
    }

    public static PropertyMap<Like, LikeDTO> entityToDTOPropertyMap = new PropertyMap<Like, LikeDTO>() {
        @Override
        protected void configure() {
            map(source.getMember().getId(), destination.getMemberID());
            map(source.getPost().getId(), destination.getPostID());
        }
    };

    public static PropertyMap<LikeDTO, Like> dtoToEntityPropertyMap = new PropertyMap<LikeDTO, Like>() {
        @Override
        protected void configure() {
            map(source.getMemberID(), destination.getMember().getId());
            map(source.getPostID(), destination.getPost().getId());
        }
    };
}
