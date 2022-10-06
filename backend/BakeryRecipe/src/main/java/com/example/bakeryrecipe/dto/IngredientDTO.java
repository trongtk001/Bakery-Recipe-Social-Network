package com.example.bakeryrecipe.dto;

import java.io.Serializable;
import java.util.Objects;

public class IngredientDTO implements Serializable {
    private Long id;
    private String ingredients;
    private String description;
    private String unit;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String ingredients, String description, String unit) {
        this.id = id;
        this.ingredients = ingredients;
        this.description = description;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
