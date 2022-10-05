package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Post;


public class PostImageDTO {
    private Long id;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
