package com.example.bakeryrecipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredients_id", nullable = false)
    private Long id;

    @Column(name = "ingredients", columnDefinition = "nvarchar(50)", nullable = false)
    private String ingredients;

    @Column(name = "description", columnDefinition = "nvarchar(500)", nullable = false, length = 500)
    private String description;

    @Column(name = "Unit", columnDefinition = "nvarchar(50)",nullable = false, length = 50)
    private String unit;

    @OneToMany(mappedBy = "ingredients", cascade = CascadeType.REMOVE)
    private List<StepIngredient> stepIngredientList;

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

    public List<StepIngredient> getStepIngredientList() {
        return stepIngredientList;
    }

    public void setStepIngredientList(List<StepIngredient> stepIngredientList) {
        this.stepIngredientList = stepIngredientList;
    }
}