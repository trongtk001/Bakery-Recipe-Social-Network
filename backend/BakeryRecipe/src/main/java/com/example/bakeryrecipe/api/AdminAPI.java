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
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/admin")
@PreAuthorize("isAuthenticated()")
public class AdminAPI {
    private final MemberService memberService;
    private final PostService postService;
    public  AdminAPI(MemberService memberService, PostService postService){
        this.memberService = memberService;
        this.postService = postService;
    }
    @GetMapping("/member")
    public ListMemberOutput getMembers(@RequestParam("page") int page, @RequestParam("size") int size ){
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<MemberDTO> memberList=  memberService.searchMemberByName("",pageable);
        return new ListMemberOutput(page,size,memberList.getTotalPages(),memberList.getContent());
    }
    @GetMapping("/member")
    public ListMemberOutput getMembers(@RequestParam("name") String q, @RequestParam("page") int page, @RequestParam("size") int size ){
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<MemberDTO> memberList=  memberService.searchMemberByName(q,pageable);
        return new ListMemberOutput(page,size,memberList.getTotalPages(),memberList.getContent());
    }
    @GetMapping("/member/{id}")
    public MemberDTO getMember(@PathVariable("id") Long id){
        return memberService.search(id);
    }
    @GetMapping("/post/{id}")
    public ListPostOutput getPostsByMember(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<PostDTO> postDTOS = postService.findAllByMemberId(id, pageable);
        return new ListPostOutput(page, size, postDTOS.getTotalPages(), postDTOS.getContent());
    }
    @DeleteMapping("post/{id}")
    @PreAuthorize("isAuthenticated()")
    public PostDTO deletePost(@PathVariable("id") Long id){
        return postService.delete(id);
    }

}
