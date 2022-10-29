package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.Step;
import com.example.bakeryrecipe.entity.StepIngredient;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.repository.IngredientRepository;
import com.example.bakeryrecipe.repository.StepIngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StepIngredientService {

    private final IngredientMapper mapper;
    private final StepIngredientRepository stepIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public StepIngredientService(IngredientMapper mapper, StepIngredientRepository stepIngredientRepository, IngredientRepository ingredientRepository) {
        this.mapper = mapper;
        this.stepIngredientRepository = stepIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDTO> save(Step step, List<IngredientDTO> ingredientDTOS) {
        return ingredientDTOS.stream().map(ingredientDTO -> {

            Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient")
            );

            StepIngredient stepIngredient = new StepIngredient(ingredient, step, ingredientDTO.getQuantity());
            stepIngredient = stepIngredientRepository.save(stepIngredient);
            IngredientDTO newIngredient = mapper.toDTO(stepIngredient.getIngredients());
            newIngredient.setQuantity(stepIngredient.getQuantity());
            return newIngredient;
        }).collect(Collectors.toList());
    }
}
