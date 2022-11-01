package com.example.bakeryrecipe.dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable {
    private Long id;
    private String ingredients;
    private String description;
    private String unit;
    private int quantity;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String ingredients, String description, String unit, int quantity) {
        this.id = id;
        this.ingredients = ingredients;
        this.description = description;
        this.unit = unit;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
