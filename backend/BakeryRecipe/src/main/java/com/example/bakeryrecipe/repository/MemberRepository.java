package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findOneByUsername(String username);

    Optional<Member> findOneByEmail(String email);

    @Query("select m from Member m where m.name = ?1")
    Page<Member> findAllByName(String name, Pageable pageable);
}