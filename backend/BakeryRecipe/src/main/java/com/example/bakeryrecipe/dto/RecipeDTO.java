package com.example.bakeryrecipe.dto;


import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class RecipeDTO implements Serializable {
    private Long id;
    private String name;
    private List<StepDTO> steps;
    private List<Long> toolsId;
    private List<IngredientDTO> ingredients;
    private List<ToolDTO> tools;

    public RecipeDTO() {
    }

    public RecipeDTO(Long id) {
        this.id = id;
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

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }

    public List<Long> getToolsId() {
        return toolsId;
    }

    public void setToolsId(List<Long> toolsId) {
        this.toolsId = toolsId;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<ToolDTO> getTools() {
        return tools;
    }

    public void setTools(List<ToolDTO> tools) {
        this.tools = tools;
    }

    public List<ToolDTO> toolDTOS() {
        if (isNull(this.toolsId)) {
            return null;
        }

        return this.toolsId.stream().map(id -> new ToolDTO(id)).collect(Collectors.toList());
    }
}
