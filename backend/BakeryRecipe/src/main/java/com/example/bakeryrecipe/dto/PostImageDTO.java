package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Post;


public class PostImageDTO {
    private Long id;
    private Post post;
    private String image;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
