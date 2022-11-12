package com.example.bakeryrecipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class RecipeTool {
    @EmbeddedId
    private RecipeToolId recipeToolId;

    @MapsId("toolId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @MapsId("recipeId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeTool() {
    }

    public RecipeTool(Tool tool, Recipe recipe) {
        this.recipeToolId = new RecipeToolId(tool.getId(), recipe.getId());
        this.tool = tool;
        this.recipe = recipe;
    }

    public RecipeToolId getRecipeToolId() {
        return recipeToolId;
    }

    public void setRecipeToolId(RecipeToolId recipeToolId) {
        this.recipeToolId = recipeToolId;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
