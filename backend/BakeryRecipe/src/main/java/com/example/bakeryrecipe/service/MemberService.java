package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService implements BaseService<MemberDTO> {


    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberMapper mapper;
    private final RoleService roleService;
    private final MemberRoleService memberRoleService;

    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberMapper mapper, RoleService roleService, MemberRoleService memberRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.mapper = mapper;
        this.roleService = roleService;
        this.memberRoleService = memberRoleService;
    }

    @Override
    public MemberDTO save(MemberDTO dto) {
        Member memberEntity;
        if (null == dto.getId()) {
            //create new member
            memberEntity = memberRepository.findOneByUsername(dto.getUsername()).orElse(null);
            if (null == memberEntity) {
                memberEntity = mapper.toEntity(dto);
                memberEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
                memberRepository.save(memberEntity);
                memberRoleService.save(memberEntity, dto.getRoles());
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with the same identity already exists");
            }
        } else {
            //edit member info
            memberEntity = memberRepository.findById(dto.getId()).orElse(null);
            if (null != memberEntity) {
                memberEntity = mapper.toEntity(dto, memberEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
            }
        }
        MemberDTO memberDTO = new MemberDTO();
        return mapper.toDTO(memberEntity);
    }

    @Override
    public MemberDTO delete(long id) {
        return null;
    }

    @Override
    public MemberDTO search(Long id) {
        return null;
    }

    public MemberDTO searchMemberByID(Long id) {
        return null;
    }

    public MemberDTO searchMemberByUsername(String username) {
        return mapper.toDTO(memberRepository.findOneByUsername(username).orElse(null));
    }
}
