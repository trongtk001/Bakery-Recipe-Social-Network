package com.example.bakeryrecipe.dto;

import java.io.Serializable;

public class FollowDTO implements Serializable {

    private Long id;
    private Long memberID;
    private Long followerID;
    private FollowAction action;

    public FollowDTO() {
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

    public Long getFollowerID() {
        return followerID;
    }

    public void setFollowerID(Long followerID) {
        this.followerID = followerID;
    }

    public FollowAction getAction() {
        return action;
    }

    public void setAction(FollowAction action) {
        this.action = action;
    }

    public enum FollowAction {
        FOLLOW,
        UNFOLLOW
    }
}
