package com.example.bakeryrecipe.service.impl;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.repository.RoleRepository;
import com.example.bakeryrecipe.service.MemberRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleServiceImpl implements MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;
    private final RoleRepository roleRepository;

    public MemberRoleServiceImpl(MemberRoleRepository memberRoleRepository, RoleRepository roleRepository) {
        this.memberRoleRepository = memberRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public MemberRole save(MemberRole dto) {
        return null;
    }

    @Override
    public MemberRole edit(MemberRole dto) {
        return null;
    }

    @Override
    public MemberRole delete(long id) {
        return null;
    }

    @Override
    public MemberRole search(Long id) {
        return null;
    }

    @Override
    public void save(Member memberEntity, List<String> roles) {
        roles.forEach(role -> {
            Role roleEntity = roleRepository.findOneByRoleName(role).orElse(null);
            if (null != roleEntity)
                memberRoleRepository.save(new MemberRole(memberEntity, roleEntity));
        });
    }
}
