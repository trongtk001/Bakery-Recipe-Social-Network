package com.example.bakeryrecipe.service.impl;

import com.example.bakeryrecipe.authentication.JwtUtils;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.MemberRole;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.repository.MemberRepository;
import com.example.bakeryrecipe.service.MemberRoleService;
import com.example.bakeryrecipe.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberServiceImpl implements MemberService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberMapper mapper;
    private final MemberRoleService memberRoleService;

    public MemberServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberMapper mapper, MemberRoleService memberRoleService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.mapper = mapper;
        this.memberRoleService = memberRoleService;
    }

    @Override
    public ResponseCookie Login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtCookie(username);
    }

    @Override
    public MemberDTO save(MemberDTO dto) {
        Member entity;
        if (null == dto.getId()) {
            //create new member
            entity = memberRepository.findOneByUsername(dto.getUsername()).orElse(null);
            if (null == entity) {
                entity = mapper.toEntity(dto);
                entity.setPassword(passwordEncoder.encode(dto.getPassword()));
                memberRepository.save(entity);
                memberRoleService.save(entity, dto.getRoles());
            }
        } else {
            //edit member info
            entity = memberRepository.findById(dto.getId()).orElse(null);
            if (null != entity) {
                entity = mapper.toEntity(dto, entity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
            }
        }
        return mapper.toDTO(entity);
    }

    @Override
    public MemberDTO delete(long id) {
        return null;
    }

    @Override
    public MemberDTO search(Long id) {
        return null;
    }
}
