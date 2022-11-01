package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("test")
@PreAuthorize("permitAll()")
public class Test {

    @GetMapping()
    public String getPost() {
        return "Welcome";
    }

    @Autowired
    MemberMapper mapper;

    @PostMapping("member")
    public MemberDTO getMember(@RequestBody MemberDTO memberDTO) {
        Member member = mapper.toEntity(memberDTO);
        System.out.println(member);
        return mapper.toDTO(member);
    }

}
