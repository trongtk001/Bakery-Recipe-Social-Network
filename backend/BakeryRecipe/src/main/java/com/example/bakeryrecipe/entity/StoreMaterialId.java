package com.example.bakeryrecipe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoreMaterialId implements Serializable {
    private static final long serialVersionUID = 2731727887242800911L;
    @Column(name = "material_id", nullable = false)
    private Long materialId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoreMaterialId entity = (StoreMaterialId) o;
        return Objects.equals(this.materialId, entity.materialId) &&
                Objects.equals(this.storeId, entity.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, storeId);
    }

}