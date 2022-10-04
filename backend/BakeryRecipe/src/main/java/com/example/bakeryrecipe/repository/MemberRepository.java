package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findOneByUsername(String username);

    Page<Member> findAllByName(String name, Pageable pageable);
}