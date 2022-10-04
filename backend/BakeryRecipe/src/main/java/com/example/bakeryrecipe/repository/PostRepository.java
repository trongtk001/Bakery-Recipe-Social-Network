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
    Post findPostByMemberId(Long id);

    Post findPostsById(Long id);

    List<Post> findAllByMemberId(Long id);

    List<Post> findAll();

    @Modifying
    @Transactional
    Post deletePostById(long id);


    @Query("select p from Post p where p.postBody LIKE CONCAT('%',:name,'%') or p.member.name LIKE CONCAT('%',:name,'%')")
    List<Post> findAllByPostBodyOrMember_Name(String name);
}