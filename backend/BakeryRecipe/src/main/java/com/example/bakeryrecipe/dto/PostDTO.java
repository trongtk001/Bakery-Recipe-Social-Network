package com.example.bakeryrecipe.dto;

import java.time.Instant;

public class PostDTO {
    private Long id;
//    private String createBy;
//    private Instant createDate;
    private String postBody;

    private String image;

    private String video;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public Instant getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Instant createDate) {
//        this.createDate = createDate;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
