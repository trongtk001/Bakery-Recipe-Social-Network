package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findOneByRoleName(String roleName);

}