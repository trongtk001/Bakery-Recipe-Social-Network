package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StepIngredientId implements Serializable {
    private static final long serialVersionUID = 1683237921962086574L;
    @Column(name = "ingredients_id", nullable = false)
    private Long ingredientsId;

    @Column(name = "step_id", nullable = false)
    private Long stepId;

    public StepIngredientId() {
    }

    public StepIngredientId(Long ingredientsId, Long stepId) {
        this.ingredientsId = ingredientsId;
        this.stepId = stepId;
    }

    public Long getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Long ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StepIngredientId entity = (StepIngredientId) o;
        return Objects.equals(this.ingredientsId, entity.ingredientsId) &&
                Objects.equals(this.stepId, entity.stepId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, stepId);
    }

}