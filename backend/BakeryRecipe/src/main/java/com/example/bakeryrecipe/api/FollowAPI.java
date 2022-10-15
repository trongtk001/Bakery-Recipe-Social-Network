package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/follow")
@PreAuthorize("permitAll()")
public class FollowAPI {
    @Autowired
    FollowService followService;

    @PostMapping("")
    public FollowDTO createNewFollow(@RequestBody FollowDTO followDTO){
        return followService.save(followDTO);
    }

    @GetMapping("friends/{id}")
    public List<FollowDTO> listFriends(@PathVariable("id") Long id){
        return followService.findAllFriend(id);
    }

    @GetMapping("follows/{id}")
    public List<FollowDTO> listFollowers(@PathVariable("id") Long id){
        return followService.findAllFollower(id);
    }
}
