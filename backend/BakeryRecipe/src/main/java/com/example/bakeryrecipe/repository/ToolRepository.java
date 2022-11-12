package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    @Query("select t from Tool t where t.name = ?1")
    Optional<Tool> findByName(String name);
}