package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.service.S3Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/test")
@PreAuthorize("permitAll()")
public class Test {

    public final MemberRoleRepository memberRoleRepository;

    public final S3Service s3Service;

    public Test(MemberRoleRepository memberRoleRepository, S3Service s3Service) {
        this.memberRoleRepository = memberRoleRepository;
        this.s3Service = s3Service;
    }

    @GetMapping("/test")
    public String test() {
        return "hahahaha";
    }
}
