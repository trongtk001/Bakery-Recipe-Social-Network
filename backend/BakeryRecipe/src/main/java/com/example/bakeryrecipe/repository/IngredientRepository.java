package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select i from Ingredient i where i.ingredients = ?1")
    Optional<Ingredient> findByIngredients(String name);

    @Query("select i from Ingredient i where i.ingredients like concat('%', ?1, '%')")
    List<Ingredient> searchAllByIngredientsContaining(String name);

}