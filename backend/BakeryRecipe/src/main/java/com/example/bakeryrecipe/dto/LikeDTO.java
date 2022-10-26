package com.example.bakeryrecipe.dto;


import java.io.Serializable;

public class LikeDTO implements Serializable {

    private Long id;

    private Long memberID;

    private Long postID;

    private LikeAction action;

    public LikeDTO() {
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

    public LikeAction getAction() {
        return action;
    }

    public void setAction(LikeAction action) {
        this.action = action;
    }

    public enum LikeAction {
        LIKE,
        UNLIKE
    }
}

