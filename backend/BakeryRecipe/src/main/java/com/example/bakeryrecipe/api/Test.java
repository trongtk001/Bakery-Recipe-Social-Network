package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.EmojiDTO;
import com.example.bakeryrecipe.repository.MemberRoleRepository;
import com.example.bakeryrecipe.service.EmojiService;
import com.example.bakeryrecipe.service.PostService;
import com.example.bakeryrecipe.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/test")
@PreAuthorize("permitAll()")
public class Test {

    public final MemberRoleRepository memberRoleRepository;

    public final S3Service s3Service;

    @Autowired
    PostService postService;

    @Autowired
    EmojiService emojiService;

    public Test(MemberRoleRepository memberRoleRepository, S3Service s3Service) {
        this.memberRoleRepository = memberRoleRepository;
        this.s3Service = s3Service;
    }

    @GetMapping("")
    public List<EmojiDTO> test() {
        return emojiService.findAllByPost(1L);
    }
}
