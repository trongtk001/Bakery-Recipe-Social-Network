package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}