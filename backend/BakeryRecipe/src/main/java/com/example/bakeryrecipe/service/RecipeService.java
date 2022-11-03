package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.dto.StepDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class RecipeService implements BaseService<RecipeDTO>{

    private final RecipeMapper mapper;

    private final RecipeRepository recipeRepository;

    private final StepService stepService;

    public RecipeService(RecipeMapper mapper, RecipeRepository recipeRepository, StepService stepService) {
        this.mapper = mapper;
        this.recipeRepository = recipeRepository;
        this.stepService = stepService;
    }

    public RecipeDTO save(Post post, RecipeDTO recipeDTO){
        Recipe recipe = mapper.toEntity(recipeDTO);
        recipe.setPost(post);
        recipe = recipeRepository.save(recipe);

        List<StepDTO> stepDTOS = nonNull(recipeDTO.getSteps()) ? stepService.save(recipe, recipeDTO.getSteps()) : null;

        recipeDTO = mapper.toDTO(recipe);
        recipeDTO.setSteps(stepDTOS);
        return recipeDTO;
    }

    @Override
    public RecipeDTO save(RecipeDTO dto) {
        RecipeDTO recipeDTO;
        Recipe recipe = mapper.toEntity(dto);
        recipe = recipeRepository.save(recipe);

        recipeDTO = mapper.toDTO(recipe);
        return recipeDTO;
    }

    @Override
    public RecipeDTO update(RecipeDTO dto) {
        return null;
    }

    public RecipeDTO delete(long id) {
        return null;
    }

    public RecipeDTO search(Long id) {
        return null;
    }
}
