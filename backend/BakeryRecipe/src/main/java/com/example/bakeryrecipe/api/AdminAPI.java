package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListMemberOutput;
import com.example.bakeryrecipe.api.output.ListPostOutput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.MemberService;
import com.example.bakeryrecipe.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminAPI {

    private final MemberService memberService;
    private final PostService postService;

    public  AdminAPI(MemberService memberService, PostService postService){
        this.memberService = memberService;
        this.postService = postService;
    }
    @GetMapping("/member")
    public ListMemberOutput getMembers(@RequestParam("page") int page, @RequestParam("size") int size ){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberDTO> memberList=  memberService.searchMemberByName("",pageable);
        return new ListMemberOutput(page,size,memberList.getTotalPages(),memberList.getContent());
    }

    @GetMapping("/member/{id}")
    public MemberDTO getMember(@PathVariable("id") Long id){
        return memberService.search(id);
    }

    @GetMapping("/post/{id}")
    public ListPostOutput getPostsByMember(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PostDTO> postDTOS = postService.findAllByMemberId(id, pageable);
        return new ListPostOutput(page, size, postDTOS.getTotalPages(), postDTOS.getContent());
    }

    @DeleteMapping("post/{id}")
    @PreAuthorize("isAuthenticated()")
    public PostDTO deletePost(@PathVariable("id") Long id){
        return postService.delete(id);
    }
}
