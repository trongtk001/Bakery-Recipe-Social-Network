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

    @Query("select m from Member m where m.name like concat('%', ?1, '%')")
    Page<Member> findAllByNameContaining(String name, Pageable pageable);

    Member findMemberById(Long id);

    @Query("select m from Member m where m.verificationCode = ?1")
    Member findMemberByVerificationCode(String code);
}