package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Post;


public class PostImageDTO {
    private Long id;
    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
