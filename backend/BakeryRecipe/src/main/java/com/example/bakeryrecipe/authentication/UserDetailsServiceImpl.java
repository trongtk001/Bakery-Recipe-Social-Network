package com.example.bakeryrecipe.authentication;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Role;
import com.example.bakeryrecipe.repository.MemberRepository;
import com.example.bakeryrecipe.repository.MemberRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findOneByUsername(username).orElse(null);

        List<Role> roles = memberRoleRepository.findAllByMemberId(member.getId()).stream().map(memberRole -> memberRole.getRole()).collect(Collectors.toList());

        if (null == member) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return UserDetailsImpl.build(member, roles);
    }
}
