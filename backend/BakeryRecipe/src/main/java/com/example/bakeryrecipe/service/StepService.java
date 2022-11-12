package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.StepDTO;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.Step;
import com.example.bakeryrecipe.mapper.StepMapper;
import com.example.bakeryrecipe.repository.StepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class StepService implements BaseService<StepDTO> {

    private final StepRepository stepRepository;
    private final StepMapper mapper;

    public StepService(StepRepository stepRepository, StepMapper mapper) {
        this.stepRepository = stepRepository;
        this.mapper = mapper;
    }

    @Override
    public StepDTO save(StepDTO dto) {
        Step step = mapper.toEntity(dto);
        step = stepRepository.save(step);

        dto = mapper.toDTO(step);
        return dto;
    }

    public List<StepDTO> save(Recipe recipe, List<StepDTO> stepDTOList) {
        return stepDTOList.stream().map(stepDTO -> {
            Step step = mapper.toEntity(stepDTO);
            step.setRecipe(recipe);
            step = stepRepository.save(step);


            stepDTO = mapper.toDTO(step);
            return stepDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public StepDTO update(StepDTO dto) {
        return null;
    }

    @Override
    public StepDTO delete(long id) {
        return null;
    }
}
