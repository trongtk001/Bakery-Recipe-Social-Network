package com.example.bakeryrecipe.dto;


import java.util.List;

public class RecipeDTO {
    private Long id;

    private String name;

    private String description;

    private List<String> steps;

    private List<String> tool;

    private List<IngredientDTO> ingredients;

    public RecipeDTO() {
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

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getTool() {
        return tool;
    }

    public void setTool(List<String> tool) {
        this.tool = tool;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String stepsToString() {
        return String.join("/n", this.steps);
    }

    public String toolToString() {
        return String.join("/n", this.tool);
    }
}
