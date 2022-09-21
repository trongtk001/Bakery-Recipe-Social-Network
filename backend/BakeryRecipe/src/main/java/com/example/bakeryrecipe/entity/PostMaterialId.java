package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostMaterialId implements Serializable {
    private static final long serialVersionUID = -798812117097808166L;
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "material_id", nullable = false)
    private Long materialId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostMaterialId entity = (PostMaterialId) o;
        return Objects.equals(this.postId, entity.postId) &&
                Objects.equals(this.materialId, entity.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, materialId);
    }

}