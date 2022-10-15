package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Member;

import java.io.Serializable;

public class FollowDTO implements Serializable {

    private Long id;
    private MemberDTO member;
    private MemberDTO follower;
    private Integer status;

    public FollowDTO() {
    }

    public FollowDTO(Long id, MemberDTO member, MemberDTO follower, Integer status) {
        this.id = id;
        this.member = member;
        this.follower = follower;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public MemberDTO getFollower() {
        return follower;
    }

    public void setFollower(MemberDTO follower) {
        this.follower = follower;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
