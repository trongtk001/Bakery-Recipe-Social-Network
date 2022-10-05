package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Post;



public class PostVideoDTO {
    private Long id;

    private String video;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
