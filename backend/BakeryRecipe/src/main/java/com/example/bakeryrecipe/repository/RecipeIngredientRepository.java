package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.RecipeIngredient;
import com.example.bakeryrecipe.entity.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId> {
}