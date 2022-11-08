package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListMemberOutput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("member")
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
    @PreAuthorize("isAuthenticated() && #id.equals(authentication.principal.id)")
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

    @GetMapping("/top")
    public List<MemberDTO> listTop5Member(){
        return memberService.searchTop5Member();
    }

    @DeleteMapping("/{id}")
    public MemberDTO deleteMember(@PathVariable("id") Long id){
        return memberService.delete(id);
    }
}
