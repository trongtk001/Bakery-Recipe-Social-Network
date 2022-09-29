package com.example.bakeryrecipe.service;


import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    protected Role findRoleEntityByRoleName(String roleName) {
        return roleRepository.findOneByRoleName(roleName).orElse(null);
    }
}
