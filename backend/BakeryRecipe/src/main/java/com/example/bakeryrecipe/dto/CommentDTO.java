package com.example.bakeryrecipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


public class CommentDTO implements Serializable {
    private Long id;
    private String commentDetail;
    private String image;
    private String video;
    private MemberDTO member;
    private Long postID;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String commentDetail, String image, String video, MemberDTO member, Long postID) {
        this.id = id;
        this.commentDetail = commentDetail;
        this.image = image;
        this.video = video;
        this.member = member;
        this.postID = postID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

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

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }
}
