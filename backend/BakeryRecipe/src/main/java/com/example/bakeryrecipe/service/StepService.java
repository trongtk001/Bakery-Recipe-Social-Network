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
    private final StepIngredientService stepIngredientService;

    public StepService(StepRepository stepRepository, StepMapper mapper, StepIngredientService stepIngredientService) {
        this.stepRepository = stepRepository;
        this.mapper = mapper;
        this.stepIngredientService = stepIngredientService;
    }

    @Override
    public StepDTO save(StepDTO dto) {
        Step step = mapper.toEntity(dto);
        step = stepRepository.save(step);
        List<IngredientDTO> ingredientDTOS = stepIngredientService.save(step, dto.getIngredients());

        dto = mapper.toDTO(step);
        dto.setIngredients(ingredientDTOS);
        return dto;
    }

    public List<StepDTO> save(Recipe recipe, List<StepDTO> stepDTOList) {
        return stepDTOList.stream().map(stepDTO -> {
            Step step = mapper.toEntity(stepDTO);
            step.setRecipe(recipe);
            step = stepRepository.save(step);

            List<IngredientDTO> ingredientDTOS = nonNull(stepDTO.getIngredients()) ? stepIngredientService.save(step, stepDTO.getIngredients()) : null;


            stepDTO = mapper.toDTO(step);
            stepDTO.setIngredients(ingredientDTOS);
            return stepDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public StepDTO update(StepDTO dto) {
        return null;
    }

    @Override
    public StepDTO delete(StepDTO dto) {
        return null;
    }
}
