package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
