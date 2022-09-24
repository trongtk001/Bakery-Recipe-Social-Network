package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoreIngredientId implements Serializable {
    private static final long serialVersionUID = 9091420525183758216L;
    @Column(name = "ingredients_id", nullable = false)
    private Long ingredientsId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    public Long getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Long ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoreIngredientId entity = (StoreIngredientId) o;
        return Objects.equals(this.ingredientsId, entity.ingredientsId) &&
                Objects.equals(this.storeId, entity.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, storeId);
    }

}