package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "select top 15 p.post_id,p.member_id,p.post_body,p.create_date,count(l.post_id) as sl from Post p,[Like] l where p.post_id = l.post_id and l.status = 1\n" +
            "group by p.post_id,p.member_id,p.post_body,p.create_date\n" +
            "order by count(l.post_id) DESC")
    List<Post> findAllByLike();

    @Query("select p from Post p where p.postBody LIKE CONCAT('%',:name,'%') or p.member.name LIKE CONCAT('%',:name,'%')")
    List<Post> findAllByPostBodyOrMember_Name(String name);


    @Query("select p from Post p where p.postBody LIKE CONCAT('%',:q,'%') or p.member.name LIKE CONCAT('%',:q,'%')")
    Page<Post> findAllByPostBodyOrMember_Name(String q, Pageable pageable);

    @Query("select p from Post p where p.member.id in ?1")
    Page<Post> findAllByMember_IdIn(List<Long> memberIDs, Pageable pageable);

}