package com.example.bakeryrecipe.dto;

import com.example.bakeryrecipe.entity.Member;

public class FollowDTO {

    private Long id;
    private Long memberId;
    private Long followerId;
    private Integer status;

    public FollowDTO() {
    }

    public FollowDTO(Long id, Long memberId, Long followerId, Integer status) {
        this.id = id;
        this.memberId = memberId;
        this.followerId = followerId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
