package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.service.LikeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/like")
@PreAuthorize("permitAll()")
public class LikeAPI {
    private final LikeService likeService;

    public LikeAPI(LikeService likeService) {
        this.likeService = likeService;
    }

    @PreAuthorize("isAuthenticated() && #dto.memberID.equals(authentication.principal.id)")
    @PostMapping("")
    public LikeDTO like(@RequestBody LikeDTO dto){
        return likeService.save(dto);
    }

    @GetMapping("")
    public List<LikeDTO> getEmojiByPost(@RequestParam("id") Long id){
        return likeService.findAllByPost(id);
    }

}
