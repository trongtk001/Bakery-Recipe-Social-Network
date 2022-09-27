package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
@PreAuthorize("permitAll()")
public class PostAPI {
    @Autowired
    PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return postService.save(postDTO);
    }
}
