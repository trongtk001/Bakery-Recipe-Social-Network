package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.RecipeTool;
import com.example.bakeryrecipe.entity.RecipeToolId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeToolRepository extends JpaRepository<RecipeTool, RecipeToolId> {
}