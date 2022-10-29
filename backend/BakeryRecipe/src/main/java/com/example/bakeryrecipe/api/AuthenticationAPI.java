package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.input.LoginInput;
import com.example.bakeryrecipe.authentication.JwtUtils;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.service.mailservice.ClientService;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/authentication")
@PreAuthorize("permitAll()")
public class AuthenticationAPI {

    private final MemberService memberService;

    private final ClientService clientService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    public AuthenticationAPI(MemberService memberService, ClientService clientService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.memberService = memberService;
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public MemberDTO login(@RequestBody LoginInput loginInput) {
        String username = loginInput.getUsername();
        String password = loginInput.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberDTO memberDTO = memberService.searchMemberByUsername(username);
        memberDTO.setToken(jwtUtils.generateTokenFromUsername(username));

        return memberDTO;
    }

    @PostMapping("/register")
    public void register(@RequestBody MemberDTO dto) {
        memberService.save(dto);
        // send email
        dto.setToken(jwtUtils.generateTokenFromUsername(dto.getUsername()));
        clientService.create(dto);
    }

    @GetMapping("/register/verify")
    public String verify(@RequestParam("code") String code){
        memberService.checkCode(code);
        return "activated success!!";
    }
}
