package com.example.bakeryrecipe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeIngredientId implements Serializable {
    private static final long serialVersionUID = 1543237921962083412L;

    @Column(name = "ingredients_id", nullable = false)
    private Long ingredientsId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    public RecipeIngredientId() {
    }

    public RecipeIngredientId(Long ingredientsId, Long recipeId) {
        this.ingredientsId = ingredientsId;
        this.recipeId = recipeId;
    }

    public Long getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Long ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientId that = (RecipeIngredientId) o;
        return Objects.equals(ingredientsId, that.ingredientsId) && Objects.equals(recipeId, that.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, recipeId);
    }
}
