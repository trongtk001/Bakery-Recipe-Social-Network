package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
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

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public PostDTO updatePost(@RequestBody PostDTO postDTO,@PathVariable("id") Long id){
        postDTO.setId(id);
        return postService.update(postDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public PostDTO deletePost(@PathVariable("id") Long id){
        return postService.delete(id);
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
