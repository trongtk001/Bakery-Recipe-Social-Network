package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@PreAuthorize("permitAll()")
public class PostAPI {
    @Autowired
    PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        postService.save(postDTO);
        return postDTO;
    }

    @GetMapping
    public List<PostDTO> listPost(){
        return postService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public List<PostDTO> listPostUser(@PathVariable("id") long id){
        return postService.findAllByMemberId(id);
    }
}
