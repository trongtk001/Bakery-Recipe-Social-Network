package com.example.bakeryrecipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Material {
    @Id
    @Column(name = "material_id", nullable = false)
    private Long id;

    @Column(name = "material_name", nullable = false, length = 50)
    private String materialName;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}