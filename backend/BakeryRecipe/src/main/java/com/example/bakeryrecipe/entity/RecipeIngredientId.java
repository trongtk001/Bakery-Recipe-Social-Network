package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeIngredientId implements Serializable {
    private static final long serialVersionUID = 1683237921962086574L;
    @Column(name = "ingredients_id", nullable = false)
    private Long ingredientsId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    public RecipeIngredientId() {
    }

    public RecipeIngredientId(Long ingredientsId, Long postId) {
        this.ingredientsId = ingredientsId;
        this.postId = postId;
    }

    public Long getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Long ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RecipeIngredientId entity = (RecipeIngredientId) o;
        return Objects.equals(this.ingredientsId, entity.ingredientsId) &&
                Objects.equals(this.postId, entity.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsId, postId);
    }

}