package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendId implements Serializable {
    private static final long serialVersionUID = 6203432439783187794L;
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "frend_id", nullable = false)
    private Long frendId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getFrendId() {
        return frendId;
    }

    public void setFrendId(Long frendId) {
        this.frendId = frendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FriendId entity = (FriendId) o;
        return Objects.equals(this.frendId, entity.frendId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frendId, memberId);
    }

}