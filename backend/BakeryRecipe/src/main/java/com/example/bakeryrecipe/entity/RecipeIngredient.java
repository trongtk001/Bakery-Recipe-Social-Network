package com.example.bakeryrecipe.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe_Ingredient")
public class RecipeIngredient {
    @EmbeddedId
    private RecipeIngredientId id;

    @MapsId("ingredientsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredients_id", nullable = false)
    private Ingredient ingredients;

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Recipe post;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredients, Recipe post, Integer quantity) {
        this.id = new RecipeIngredientId(ingredients.getId(), post.getId());
        this.ingredients = ingredients;
        this.post = post;
        this.quantity = quantity;
    }

    public RecipeIngredientId getId() {
        return id;
    }

    public void setId(RecipeIngredientId id) {
        this.id = id;
    }

    public Ingredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe getPost() {
        return post;
    }

    public void setPost(Recipe post) {
        this.post = post;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}