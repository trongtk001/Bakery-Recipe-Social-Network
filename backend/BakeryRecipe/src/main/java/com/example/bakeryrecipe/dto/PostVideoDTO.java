package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Post;



public class PostVideoDTO {
    private Long id;

    private Post post;

    private String video;

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
