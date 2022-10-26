package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListMemberOutput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/member")
@PreAuthorize("permitAll()")
public class MemberAPI {

    private MemberService memberService;

    public MemberAPI(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public MemberDTO getMemberInfo(@PathVariable("id") long id) {
        return memberService.search(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()&&#id.equals(authentication.principal.id)")
    public MemberDTO editMemberInfo(@PathVariable("id") Long id, @RequestBody MemberDTO memberDTO) {
        memberDTO.setId(id);
        memberDTO.setUsername(null);
        return memberService.save(memberDTO);
    }

    @GetMapping("")
    public ListMemberOutput searchMember(@RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberDTO> memberDTOS = memberService.searchMemberByName(q, pageable);
        return new ListMemberOutput(page, size, memberDTOS.getTotalPages(), memberDTOS.getContent());
    }

    @DeleteMapping("/{id}")
    public MemberDTO deleteMember(@PathVariable("id") Long id){
        return memberService.delete(id);
    }
}
