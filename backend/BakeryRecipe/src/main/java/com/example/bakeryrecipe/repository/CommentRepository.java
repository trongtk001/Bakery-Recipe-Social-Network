package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findAllByMember(Member member);
}