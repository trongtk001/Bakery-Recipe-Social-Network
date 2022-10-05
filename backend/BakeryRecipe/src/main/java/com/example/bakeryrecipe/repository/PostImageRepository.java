package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Query("select p from PostImage p where p.post.id = ?1")
    PostImage findPostImageByPostId(Long id);
}