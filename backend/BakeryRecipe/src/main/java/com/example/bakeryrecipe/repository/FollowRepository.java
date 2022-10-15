package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByMember_IdAndFollower_Id(Long id, Long aLong);

    List<Follow> findFollowByMember_Id(Long id);
}
