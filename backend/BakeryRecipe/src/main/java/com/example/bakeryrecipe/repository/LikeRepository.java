package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("select l from Like l where l.post.id = ?1 and l.member.id = ?2")
    Like findEmojiByPost_IdAndMember_Id(Long postId, Long memberId);

    @Query("select l from Like l where l.post.id = ?1 and l.status = 1")
    List<Like> findAllByPost_Id(Long id);
}
