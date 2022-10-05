package com.example.bakeryrecipe.dto;


import com.example.bakeryrecipe.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDTO {
    private Long id;

    private Post post;

    private String name;

    private String description;

    private String steps;

    private String tool;

    public RecipeDTO(Long id, Post post, String name, String description, String steps, String tool) {
        this.id = id;
        this.post = post;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.tool = tool;
    }

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
}
