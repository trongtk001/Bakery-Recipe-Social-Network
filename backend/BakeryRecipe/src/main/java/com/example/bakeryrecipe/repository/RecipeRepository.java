package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
