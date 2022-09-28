package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.entity.PostImage;
import com.example.bakeryrecipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.member.id = ?1")
    Post findPostByMemberId(Long id);

    @Query("select p from Post p where p.id = ?1")
    Post findPostsById(Long id);

    @Query("select p from Post p where p.member.id = ?1")
    List<Post> findAllByMemberId(Long id);
    @Query("select p from Post p")
    List<Post> findAll();

}