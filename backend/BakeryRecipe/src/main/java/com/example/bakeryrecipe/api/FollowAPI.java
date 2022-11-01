package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListMemberOutput;
import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.service.FollowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("follow")
@PreAuthorize("permitAll()")
public class FollowAPI {

    private final FollowService followService;

    public FollowAPI(FollowService followService) {
        this.followService = followService;
    }

    @PreAuthorize("isAuthenticated() && #followDTO.memberID.equals(authentication.principal.id)")
    @PostMapping("")
    public FollowDTO createNewFollow(@RequestBody FollowDTO followDTO){
        return followService.save(followDTO);
    }

    @GetMapping("friends/{id}")
    public ListMemberOutput listFriends(@PathVariable("id") long id, @RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberDTO> memberDTOPage = followService.findAllFriend(id, pageable);
        return new ListMemberOutput(page, size, memberDTOPage.getTotalPages(), memberDTOPage.getContent());
    }

    @GetMapping("followers/{id}")
    public ListMemberOutput listFollowers(@PathVariable("id") long id, @RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberDTO> memberDTOPage = followService.findAllFollower(id, pageable);
        return new ListMemberOutput(page, size, memberDTOPage.getTotalPages(), memberDTOPage.getContent());
    }
}
