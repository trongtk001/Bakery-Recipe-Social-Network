package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostsById(Long id);

    @Query("select p from Post p where p.member.id = ?1")
    Page<Post> findAllByMemberId(Long id, Pageable pageable);
    @Query("select p from Post p where p.member.id = ?1")
    List<Post> findAllByMemberIds(Long id);

    @Query("select p from Post p ")
    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p where p.postBody LIKE CONCAT('%',:name,'%') or p.member.name LIKE CONCAT('%',:name,'%')")
    List<Post> findAllByPostBodyOrMember_Name(String name);


    @Query("select p from Post p where p.postBody LIKE CONCAT('%',:q,'%') or p.member.name LIKE CONCAT('%',:q,'%')")
    Page<Post> findAllByPostBodyOrMember_Name(String q, Pageable pageable);

    @Query("select p from Post p where p.member.id in ?1")
    Page<Post> findAllByMember_IdIn(List<Integer> memberIDs, Pageable pageable);

}