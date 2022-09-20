package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}