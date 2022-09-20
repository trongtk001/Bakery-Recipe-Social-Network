package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.entity.MemberRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRoleId> {
}