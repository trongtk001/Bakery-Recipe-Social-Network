package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListPostOutput;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
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
    public ListPostOutput listPost(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PostDTO> postDTOS = postService.findAll(pageable);

        return new ListPostOutput(page, size, postDTOS.getTotalPages(), postDTOS.getContent());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public List<PostDTO> listPostUser(@PathVariable("id") long id){
        return postService.findAllByMemberId(id);
    }

    @GetMapping("/search")
    public ListPostOutput searchPostByName(@RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<PostDTO> postDTOS = postService.searchPostByName(q, pageable);

        return new ListPostOutput(page, size, postDTOS.getTotalPages(), postDTOS.getContent());
    }
}
