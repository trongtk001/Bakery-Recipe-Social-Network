package com.example.bakeryrecipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "name", columnDefinition = "nvarchar(50)",nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private List<Step> steps;

    @Column(name = "tool", columnDefinition = "nvarchar(500)", nullable = false, length = 500)
    private String tool;

    @OneToMany(mappedBy = "step", cascade = CascadeType.REMOVE)
    private List<StepIngredient> ingredients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public List<StepIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<StepIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> toolToList() {
        return new ArrayList<>(Arrays.asList(tool.split("/n")));
    }
}