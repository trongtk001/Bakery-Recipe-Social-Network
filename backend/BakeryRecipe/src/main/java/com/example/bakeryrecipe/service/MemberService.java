package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.authentication.AuthTokenFilter;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Autowired
     AuthTokenFilter authTokenFilter;

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
                memberEntity = memberRepository.findOneByEmail(dto.getEmail()).orElse(null);
                if (null == memberEntity) {
                    memberEntity = mapper.toEntity(dto);
                    memberEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
                    memberRepository.save(memberEntity);
                    memberRoleService.save(memberEntity, dto.getRoles());
                } else {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "This email already exists");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with the same identity already exists");
            }
        } else {
            //edit member info
            memberEntity = memberRepository.findById(dto.getId()).orElse(null);
            if (null != memberEntity) {
                memberEntity = mapper.toEntity(dto, memberEntity);
                memberEntity = memberRepository.save(memberEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
            }
        }
        return mapper.toDTO(memberEntity);
    }

    @Override
    public MemberDTO update(MemberDTO dto) {
        return null;
    }

    public MemberDTO delete(Long id) {
        Member entity = memberRepository.findMemberById(id);
        if(entity != null){
                memberRepository.delete(entity);
                return mapper.toDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this post");
        }
    }

    @Override
    public MemberDTO delete(MemberDTO dto) {
        return null;
    }

    public MemberDTO delete(long id) {
        return null;
    }

    public MemberDTO search(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
        }
        return mapper.toDTO(member);
    }

    protected Member searchEntity(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Page<MemberDTO> searchMemberByName(String q, Pageable pageable) {
        Page<Member> members = memberRepository.findAllByNameContaining(q, pageable);
        Page<MemberDTO> memberDTOS = new PageImpl<>(mapper.toDTOList(members.getContent()), pageable, members.getTotalElements());
        return memberDTOS;
    }

    public MemberDTO searchMemberByUsername(String username) {
        Member member = memberRepository.findOneByUsername(username).orElse(null);
        if (member == null && member.getIs_active() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
        }
        return mapper.toDTO(member);
    }
    // check code send to email and active member
    public void checkCode(String code){
        String userName = authTokenFilter.doFilterInternal(code);
            Member member = memberRepository.findOneByUsername(userName).orElse(null);
            if(member != null){
                member.setIs_active(true);
                memberRepository.save(member);
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"active account fail");
            }
    }
}
