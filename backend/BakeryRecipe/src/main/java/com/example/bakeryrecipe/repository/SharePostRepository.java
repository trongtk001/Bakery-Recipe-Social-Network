package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.SharePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SharePostRepository extends JpaRepository<SharePost, Long> {

    @Query("select s from SharePost s where s.member.id = ?1 and s.post.id = ?2")
    SharePost findByMember_IdAndPost_Id(long memberID, long postID);

    @Query("select s from SharePost s where s.member.id = ?1")
    Page<SharePost> findAllByMember_Id(long memberID, Pageable pageable);
}
