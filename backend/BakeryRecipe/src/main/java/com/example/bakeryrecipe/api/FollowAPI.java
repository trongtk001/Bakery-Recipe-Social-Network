package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
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

    private final FollowService followService;

    public FollowAPI(FollowService followService) {
        this.followService = followService;
    }

    @PreAuthorize("isAuthenticated() && #followDTO.follower.id.equals(authentication.principal.id)")
    @PostMapping("")
    public FollowDTO createNewFollow(@RequestBody FollowDTO followDTO){
        return followService.save(followDTO);
    }

    @GetMapping("friends/{id}")
    public List<MemberDTO> listFriends(@PathVariable("id") long id){
        return followService.findAllFriend(id);
    }

    @GetMapping("followers/{id}")
    public List<MemberDTO> listFollowers(@PathVariable("id") long id){
        return followService.findAllFollower(id);
    }
}
