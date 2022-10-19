package com.example.bakeryrecipe.dto;


import java.util.List;

public class RecipeDTO {
    private Long id;
    private String name;

    private String description;

    private String steps;

    private String tool;

    private List<IngredientDTO> ingredients;

    public RecipeDTO() {
    }

    public RecipeDTO(Long id, String name, String description, String steps, String tool, List<IngredientDTO> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.tool = tool;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
