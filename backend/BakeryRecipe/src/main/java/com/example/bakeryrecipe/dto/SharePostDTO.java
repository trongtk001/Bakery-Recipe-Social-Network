package com.example.bakeryrecipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SharePostDTO implements Serializable {
    private  Long id;
    private Long memberID;
    private Long postID;
    private  Instant createDate;
    private PostDTO post;

    public SharePostDTO() {
    }

    public SharePostDTO(Long memberID, Long postID) {
        this.memberID = memberID;
        this.postID = postID;
    }

    public SharePostDTO(Long id, Long memberID, Long postID, Instant createDate) {
        this.id = id;
        this.memberID = memberID;
        this.postID = postID;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
