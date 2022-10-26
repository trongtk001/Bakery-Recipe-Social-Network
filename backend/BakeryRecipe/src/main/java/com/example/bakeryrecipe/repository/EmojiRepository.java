package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
    @Query("select e from Emoji e where e.post.id = ?1 and e.member.id = ?2")
    Emoji findEmojiByPost_IdAndMember_Id(Long postId, Long memberId);

    @Query("select e from Emoji e where e.post.id = ?1")
    List<Emoji> findAllByPost_Id(Long id);

    @Query("select e from Emoji e where e.id = ?1")
    Emoji findEmojiById(Long id);
}
