package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.entity.Follow;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper extends BaseMapper<Follow, FollowDTO>{
    public FollowMapper(ModelMapper modelMapper) {
        super(modelMapper, Follow.class, FollowDTO.class);
    }

    public static PropertyMap<Follow, FollowDTO> entityToDTOPropertyMap = new PropertyMap<Follow, FollowDTO>() {
        @Override
        protected void configure() {
            map(source.getMember().getId(), destination.getMemberID());
            map(source.getFollower().getId(), destination.getFollowerID());
        }
    };

    public static PropertyMap<FollowDTO, Follow> dtoToEntityPropertyMap = new PropertyMap<FollowDTO, Follow>() {
        @Override
        protected void configure() {
            map(source.getMemberID(), destination.getMember().getId());
            map(source.getFollowerID(), destination.getFollower().getId());
        }
    };
}
