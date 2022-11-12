package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.entity.RecipeTool;
import com.example.bakeryrecipe.entity.Tool;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ToolMapper extends BaseMapper<Tool, ToolDTO>{

    public ToolMapper(ModelMapper modelMapper) {
        super(modelMapper, Tool.class, ToolDTO.class);
    }

    public static PropertyMap<RecipeTool, ToolDTO> entityToDTOPropertyMap = new PropertyMap<RecipeTool, ToolDTO>() {
        @Override
        protected void configure() {
            map(source.getTool().getId(), destination.getId());
            map(source.getTool().getName(), destination.getName());
        }
    };

    public static PropertyMap<ToolDTO, RecipeTool> dtoToEntityPropertyMap = new PropertyMap<ToolDTO, RecipeTool>() {
        @Override
        protected void configure() {
            map(source.getId(), destination.getTool().getId());
            map(source.getName(), destination.getTool().getName());
        }
    };
}
