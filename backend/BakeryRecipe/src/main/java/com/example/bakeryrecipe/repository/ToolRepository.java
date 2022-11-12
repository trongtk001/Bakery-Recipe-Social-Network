package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Long> {
}