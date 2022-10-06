package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.member = ?1")
    Comment findAllByMember(Member member);

    @Query("select c from Comment c where c.post.id = ?1")
    Page<Comment>  findAllByPostId(Long id, Pageable pageable);
}