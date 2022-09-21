package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.entity.MemberRoleId;
import com.example.bakeryrecipe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRoleId> {

    List<MemberRole> findAllByMemberId(Long id);
}