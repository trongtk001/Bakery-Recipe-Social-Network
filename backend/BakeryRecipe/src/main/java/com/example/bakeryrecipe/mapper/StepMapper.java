package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.StepDTO;
import com.example.bakeryrecipe.entity.Step;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StepMapper extends BaseMapper<Step, StepDTO> {
    public StepMapper(ModelMapper modelMapper) {
        super(modelMapper, Step.class, StepDTO.class);
    }
}
