package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.StepIngredient;
import com.example.bakeryrecipe.entity.StepIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepIngredientRepository extends JpaRepository<StepIngredient, StepIngredientId> {
}