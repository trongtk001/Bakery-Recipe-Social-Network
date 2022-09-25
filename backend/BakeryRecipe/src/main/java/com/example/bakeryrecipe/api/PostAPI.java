package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostAPI {
    @Autowired
    PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        postService.save(postDTO);
        return postDTO;
    }
}
