package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.dto.StepDTO;
import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class RecipeService implements BaseService<RecipeDTO>{

    private final RecipeMapper mapper;
    private final RecipeRepository recipeRepository;
    private final StepService stepService;
    private final RecipeToolService recipeToolService;
    private final RecipeIngredientsService recipeIngredientsService;

    public RecipeService(RecipeMapper mapper, RecipeRepository recipeRepository, StepService stepService, RecipeToolService recipeToolService, RecipeIngredientsService recipeIngredientsService) {
        this.mapper = mapper;
        this.recipeRepository = recipeRepository;
        this.stepService = stepService;
        this.recipeToolService = recipeToolService;
        this.recipeIngredientsService = recipeIngredientsService;
    }

    public RecipeDTO save(Post post, RecipeDTO recipeDTO){
        Recipe recipe = mapper.toEntity(recipeDTO);
        recipe.setPost(post);
        recipe.setIngredients(null);
        recipe = recipeRepository.save(recipe);

        List<StepDTO> stepDTOS = nonNull(recipeDTO.getSteps()) ? stepService.save(recipe, recipeDTO.getSteps()) : null;
        List<IngredientDTO> ingredientDTOS = nonNull(recipeDTO.getIngredients()) ? recipeIngredientsService.save(recipe, recipeDTO.getIngredients()) : null;
        List<ToolDTO> toolDTOS = nonNull(recipeDTO.toolDTOS()) ? recipeToolService.save(recipe, recipeDTO.toolDTOS()) : null;

        recipeDTO = mapper.toDTO(recipe);
        recipeDTO.setSteps(stepDTOS);
        recipeDTO.setIngredients(ingredientDTOS);
        recipeDTO.setTools(toolDTOS);
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
        return mapper.toDTO(recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")));
    }
}
