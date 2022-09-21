package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.entity.Role;

import java.util.List;

public interface MemberRoleService extends Service<MemberRole>{
    void save(Member member, List<String> roles);
}
