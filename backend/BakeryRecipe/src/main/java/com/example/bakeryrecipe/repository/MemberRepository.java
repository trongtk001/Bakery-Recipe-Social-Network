package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}