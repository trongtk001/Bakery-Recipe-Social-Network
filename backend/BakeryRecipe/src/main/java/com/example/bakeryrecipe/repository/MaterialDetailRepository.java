package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.MaterialDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDetailRepository extends JpaRepository<MaterialDetail, Long> {
}