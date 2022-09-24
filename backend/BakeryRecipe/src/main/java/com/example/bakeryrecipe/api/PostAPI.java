package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.PostDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("api/authentication")
@PreAuthorize("permitAll()")
public class PostAPI {

    @PutMapping("{id}")
    @PreAuthorize("#postDTO.")
    PostDTO editPost(@RequestBody PostDTO postDTO) {

    }
}
