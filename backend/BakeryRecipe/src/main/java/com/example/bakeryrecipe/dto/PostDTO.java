package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.PostImage;
import com.example.bakeryrecipe.entity.PostVideo;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

public class PostDTO {

    private Long id;
    private Member member;
    private Instant createDate;
    private String postBody;
    private List<PostImage> postImages;
    private List<PostVideo> postVideos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

    public List<PostVideo> getPostVideos() {
        return postVideos;
    }

    public void setPostVideos(List<PostVideo> postVideos) {
        this.postVideos = postVideos;
    }
}
