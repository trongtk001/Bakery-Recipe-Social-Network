package com.example.bakeryrecipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id", nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(500)",nullable = false)
    private String step;

    @Column(name = "description", columnDefinition = "nvarchar(3000)",nullable = false)
    private String description;

    @Column(name = "image", length = 250)
    private String image;

    @Column(name = "video", length = 250)
    private String video;

    @OneToMany(mappedBy = "step", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<StepIngredient> ingredients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<StepIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<StepIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}