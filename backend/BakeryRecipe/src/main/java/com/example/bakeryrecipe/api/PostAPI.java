package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
<<<<<<< HEAD
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
=======
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("api/authentication")
@PreAuthorize("permitAll()")
public class PostAPI {

    @PutMapping("{id}")
    @PreAuthorize("#postDTO.")
    PostDTO editPost(@RequestBody PostDTO postDTO) {

=======
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
>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0
    }
}
