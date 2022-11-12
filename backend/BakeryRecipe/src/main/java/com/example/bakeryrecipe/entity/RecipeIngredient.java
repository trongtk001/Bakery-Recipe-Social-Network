package com.example.bakeryrecipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe_Ingredient")
public class RecipeIngredient {
    @EmbeddedId
    private RecipeIngredientId recipeIngredientId;

    @MapsId("ingredientsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredients_id", nullable = false)
    private Ingredient ingredients;

    @MapsId("recipeId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredients, Recipe recipe, Integer quantity) {
        this.recipeIngredientId = new RecipeIngredientId(ingredients.getId(), recipe.getId());
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public RecipeIngredientId getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(RecipeIngredientId recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public Ingredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
