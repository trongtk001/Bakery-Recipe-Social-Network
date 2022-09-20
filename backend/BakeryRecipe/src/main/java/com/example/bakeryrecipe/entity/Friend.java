package com.example.bakeryrecipe.entity;

import javax.persistence.*;

@Entity
public class Friend {
    @EmbeddedId
    private FriendId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @MapsId("frendId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frend_id", nullable = false)
    private Member frend;

    @Column(name = "status", nullable = false)
    private Integer status;

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getFrend() {
        return frend;
    }

    public void setFrend(Member frend) {
        this.frend = frend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}