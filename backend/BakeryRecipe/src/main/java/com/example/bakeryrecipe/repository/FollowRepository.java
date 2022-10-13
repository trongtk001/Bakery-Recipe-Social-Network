package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow,Long> {
}
