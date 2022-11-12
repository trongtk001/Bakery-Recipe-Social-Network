package com.example.bakeryrecipe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeToolId implements Serializable {
    private static final long serialVersionUID = 1543237921962083412L;

    @Column(name = "tool_id")
    private Long toolId;

    @Column(name = "recipe_id")
    private Long recipeId;

    public RecipeToolId() {
    }

    public RecipeToolId(Long toolId, Long recipeId) {
        this.toolId = toolId;
        this.recipeId = recipeId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
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
        RecipeToolId that = (RecipeToolId) o;
        return Objects.equals(toolId, that.toolId) && Objects.equals(recipeId, that.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolId, recipeId);
    }
}
