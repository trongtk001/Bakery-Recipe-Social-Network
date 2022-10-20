package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.service.PostService;
import com.example.bakeryrecipe.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/test")
@PreAuthorize("permitAll()")
public class Test {

    public final MemberRoleRepository memberRoleRepository;

    public final S3Service s3Service;

    @Autowired
    PostService postService;

    public Test(MemberRoleRepository memberRoleRepository, S3Service s3Service) {
        this.memberRoleRepository = memberRoleRepository;
        this.s3Service = s3Service;
    }

    @GetMapping("")
    public String test() {
        return "hahahaha";
    }
}
