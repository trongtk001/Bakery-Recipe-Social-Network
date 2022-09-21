package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.repository.MemberRoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("api/test")
@PreAuthorize("permitAll()")
public class Test {

    public final MemberRoleRepository memberRoleRepository;

    public Test(MemberRoleRepository memberRoleRepository) {
        this.memberRoleRepository = memberRoleRepository;
    }

    @PostMapping("")
    public List<Role> test() {
        return memberRoleRepository.findAllByMemberId(3L).stream().map(memberRole -> memberRole.getRole()).collect(Collectors.toList());
    }
}
