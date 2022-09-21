package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.input.LoginInput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("api/authentication")
@PreAuthorize("permitAll()")
public class AuthenticationAPI {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationAPI(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginInput loginInput) {
        ResponseCookie cookie = memberService.Login(loginInput.getUsername(), loginInput.getPassword());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("/register")
    public void register(@RequestBody MemberDTO dto) {
        memberService.save(dto);
    }

}
