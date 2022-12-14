package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;
    private final RoleRepository roleRepository;

    public MemberRoleService(MemberRoleRepository memberRoleRepository, RoleRepository roleRepository) {
        this.memberRoleRepository = memberRoleRepository;
        this.roleRepository = roleRepository;
    }
    protected void save(Member memberEntity, List<String> roles) {
        roles.forEach(role -> {
            Role roleEntity = roleRepository.findOneByRoleName(role).orElse(null);
            if (null != roleEntity)
                memberRoleRepository.save(new MemberRole(memberEntity, roleEntity));
        });
    }
    public void delete(Member member){
        List<MemberRole> memberRole = memberRoleRepository.findAllByMemberId(member.getId());
        for(MemberRole mr : memberRole){
            memberRoleRepository.delete(mr);
        }
    }
}
