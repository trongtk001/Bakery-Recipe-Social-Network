package com.example.bakeryrecipe.dto;


import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable {
    private Long id;
    private String name;
    private List<StepDTO> steps;
    private List<String> tool;

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

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }

    public List<String> getTool() {
        return tool;
    }

    public void setTool(List<String> tool) {
        this.tool = tool;
    }

    public String toolToString() {
        return String.join("/n", this.tool);
    }
}
