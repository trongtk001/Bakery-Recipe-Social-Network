package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.StoreMaterial;
import com.example.bakeryrecipe.entity.StoreMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMaterialRepository extends JpaRepository<StoreMaterial, StoreMaterialId> {
}