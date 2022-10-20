package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.service.S3Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping("")
    public String test() {
        return "test";
    }

    @PostMapping("1")
    public String test1() {
        List<String> stringList = new ArrayList<>(Arrays.asList("1", "2", "3"));
        return String.join("/n", stringList);
    }
    @PostMapping("2")
    public List<String> test2() {
        String steps = "1/n2/n3";
        List<String> stringList = new ArrayList<>(Arrays.asList(steps.split("/n")));
        return stringList;
    }
}
