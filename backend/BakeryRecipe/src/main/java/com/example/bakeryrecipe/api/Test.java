package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.service.S3Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public String test(@RequestPart(value = "file")MultipartFile file) {
        return s3Service.uploadFile(file);
    }
}
