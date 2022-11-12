package com.example.bakeryrecipe.dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable {
    private static final long serialVersionUID = 5624337921962083412L;

    private Long id;
    private String ingredients;
    private String description;
    private String unit;
    private Integer quantity;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id) {
        this.id = id;
    }

    public IngredientDTO(Long id, String ingredients, String description, String unit, Integer quantity) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
