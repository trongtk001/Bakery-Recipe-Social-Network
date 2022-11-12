package com.example.bakeryrecipe.dto;

import java.io.Serializable;

public class ToolDTO implements Serializable {

    private Long id;
    private String name;

    public ToolDTO() {
    }

    public ToolDTO(Long id) {
        this.id = id;
    }

    public ToolDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
