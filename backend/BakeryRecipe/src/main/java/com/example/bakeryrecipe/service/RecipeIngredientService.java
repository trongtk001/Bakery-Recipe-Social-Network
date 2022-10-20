package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.RecipeIngredient;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    private final IngredientMapper mapper;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(IngredientMapper mapper, RecipeIngredientRepository recipeIngredientRepository) {
        this.mapper = mapper;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public List<IngredientDTO> save(Recipe recipe, List<IngredientDTO> ingredientDTOS) {
        return ingredientDTOS.stream().map(ingredientDTO -> {
            RecipeIngredient recipeIngredient = new RecipeIngredient(mapper.toEntity(ingredientDTO), recipe, ingredientDTO.getQuantity());
            recipeIngredient = recipeIngredientRepository.save(recipeIngredient);
            IngredientDTO newIngredient = mapper.toDTO(recipeIngredient.getIngredients());
            newIngredient.setQuantity(recipeIngredient.getQuantity());
            return newIngredient;
        }).collect(Collectors.toList());
    }
}
