package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByMember_IdAndFollower_Id(Long id, Long aLong);

    @Query("select f from Follow f where f.member.id = ?1 and f.status = 1")
    List<Follow> findAllByMember_Id(Long id);

    @Query("select f from Follow f where f.member.id = ?1 and f.status = 1")
    Page<Follow> findAllByMember_Id(Long id, Pageable pageable);

    @Query("select f from Follow f where f.follower.id = ?1 and f.status = 1")
    Page<Follow> findAllByFollower_Id(Long id, Pageable pageable);
    
}
