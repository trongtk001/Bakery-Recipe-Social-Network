package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.ListMemberOutput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/member")
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
    public MemberDTO editMemberInfo(@PathVariable("id") long id, @RequestBody MemberDTO memberDTO) {
        memberDTO.setId(id);
        memberDTO.setUsername(null);
        memberDTO.setPassword(null);
        return memberService.save(memberDTO);
    }

    @GetMapping("")
    public ListMemberOutput searchMember(@RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberDTO> memberDTOS = memberService.searchMemberByName(q, pageable);
        return new ListMemberOutput(page, size, memberDTOS.getTotalPages(), memberDTOS.getContent());
    }
}
