package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.RecipeDTO;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.mapper.RecipeMapper;
import com.example.bakeryrecipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService implements BaseService<RecipeDTO>{

    private final RecipeMapper mapper;
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeMapper mapper, RecipeRepository recipeRepository) {
        this.mapper = mapper;
        this.recipeRepository = recipeRepository;
    }

    public void saves(RecipeDTO dto){
        Recipe recipe = mapper.toEntity(dto);
        recipeRepository.save(recipe);
    }
    @Override
    public RecipeDTO save(RecipeDTO dto) {
        return null;
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
