package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.RecipeIngredient;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.repository.IngredientRepository;
import com.example.bakeryrecipe.repository.RecipeIngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientsService {
    private final IngredientMapper mapper;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeIngredientsService(IngredientMapper mapper, RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository) {
        this.mapper = mapper;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDTO> save(Recipe recipe, List<IngredientDTO> ingredientDTOS) {
        return  ingredientDTOS.stream().map(ingredientDTO -> {
            Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient")
            );
            RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, recipe,  ingredientDTO.getQuantity());
            recipeIngredient = recipeIngredientRepository.save(recipeIngredient);
            mapper.toDTO(recipeIngredient.getIngredients(), ingredientDTO);
            return ingredientDTO;
        }).collect(Collectors.toList());
    }
}
