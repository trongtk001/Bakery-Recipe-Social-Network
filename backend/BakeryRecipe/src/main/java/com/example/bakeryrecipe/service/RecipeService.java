package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService implements BaseService<RecipeDTO>{

    private final RecipeMapper mapper;

    private final RecipeRepository recipeRepository;

    private final RecipeIngredientService recipeIngredientService;

    public RecipeService(RecipeMapper mapper, RecipeRepository recipeRepository, RecipeIngredientService recipeIngredientService) {
        this.mapper = mapper;
        this.recipeRepository = recipeRepository;
        this.recipeIngredientService = recipeIngredientService;
    }

    public RecipeDTO save(Post post, RecipeDTO recipeDTO){
        Recipe recipe = mapper.toEntity(recipeDTO);
        recipe.setPost(post);
        recipe = recipeRepository.save(recipe);

        RecipeDTO newRecipeDTO = mapper.toDTO(recipe);
        List<IngredientDTO> ingredientDTOS = recipeDTO.getIngredients() != null ? recipeIngredientService.save(recipe, recipeDTO.getIngredients()) : null;
        newRecipeDTO.setIngredients(ingredientDTOS);
        return newRecipeDTO;
    }

    @Override
    public RecipeDTO save(RecipeDTO dto) {
        Recipe recipe = mapper.toEntity(dto);
        recipe = recipeRepository.save(recipe);

        RecipeDTO recipeDTO = mapper.toDTO(recipe);
        recipeDTO.setIngredients(recipeIngredientService.save(recipe, dto.getIngredients()));
        return recipeDTO;
    }

    @Override
    public RecipeDTO delete(long id) {
        return null;
    }

    @Override
    public RecipeDTO search(Long id) {
        return null;
    }
}
