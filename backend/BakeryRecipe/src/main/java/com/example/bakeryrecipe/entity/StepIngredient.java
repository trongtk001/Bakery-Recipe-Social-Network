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
@Table(name = "Step_Ingredient")
public class StepIngredient {
    @EmbeddedId
    private StepIngredientId stepIngredientId;

    @MapsId("ingredientsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredients_id", nullable = false)
    private Ingredient ingredients;

    @MapsId("stepId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "step_id", nullable = false)
    private Step step;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public StepIngredient() {
    }

    public StepIngredient(Ingredient ingredients, Step step, Integer quantity) {
        this.stepIngredientId = new StepIngredientId(ingredients.getId(), step.getId());
        this.ingredients = ingredients;
        this.step = step;
        this.quantity = quantity;
    }

    public StepIngredientId getStepIngredientId() {
        return stepIngredientId;
    }

    public void setStepIngredientId(StepIngredientId stepIngredientId) {
        this.stepIngredientId = stepIngredientId;
    }

    public Ingredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}