package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<PostDTO> listPost(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return postService.findAll(pageable);
    }

//    public Page<MemberDTO> searchMember(@RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        return memberService.searchMemberByName(q, pageable);
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public List<PostDTO> listPostUser(@PathVariable("id") long id){
        return postService.findAllByMemberId(id);
    }

    @GetMapping("/search")
    public List<PostDTO> searchPostByName(@RequestParam("name") String name){
        return postService.searchPostByName(name);
    }
}
