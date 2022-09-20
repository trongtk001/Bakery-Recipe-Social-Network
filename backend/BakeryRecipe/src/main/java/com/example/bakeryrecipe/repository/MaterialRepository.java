package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}