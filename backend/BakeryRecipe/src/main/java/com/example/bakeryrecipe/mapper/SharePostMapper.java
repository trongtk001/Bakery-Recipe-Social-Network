package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.SharePostDTO;
import com.example.bakeryrecipe.entity.SharePost;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class SharePostMapper extends BaseMapper<SharePost, SharePostDTO>{

    public SharePostMapper(ModelMapper modelMapper) {
        super(modelMapper, SharePost.class, SharePostDTO.class);
    }

    public static PropertyMap<SharePost, SharePostDTO> entityToDTOPropertyMap = new PropertyMap<SharePost, SharePostDTO>() {
        @Override
        protected void configure() {
            map(source.getMember().getId(), destination.getMemberID());
        }
    };

    public static PropertyMap<SharePostDTO, SharePost> dtoToEntityPropertyMap = new PropertyMap<SharePostDTO, SharePost>() {
        @Override
        protected void configure() {
            map(source.getMemberID(), destination.getMember().getId());
            map(source.getPostID(), destination.getPost().getId());
        }
    };
}
