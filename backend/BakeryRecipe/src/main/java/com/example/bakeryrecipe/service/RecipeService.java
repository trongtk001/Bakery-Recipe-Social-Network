package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.RecipeDTO;
import org.springframework.stereotype.Service;

@Service
public class RecipeService implements BaseService<RecipeDTO>{
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
