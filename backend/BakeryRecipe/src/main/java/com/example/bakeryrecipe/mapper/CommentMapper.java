package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.entity.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper extends BaseMapper<Comment, CommentDTO>{

    public CommentMapper(ModelMapper modelMapper) {
        super(modelMapper, Comment.class, CommentDTO.class);
    }

    public static PropertyMap<Comment, CommentDTO> entityToDTOPropertyMap = new PropertyMap<Comment, CommentDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPost());
        }
    };

}
