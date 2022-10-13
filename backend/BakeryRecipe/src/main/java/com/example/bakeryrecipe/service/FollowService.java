package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService implements BaseService<FollowDTO>{

    @Autowired
    FollowRepository followRepository;
    @Autowired
    FollowMapper mapper;

    @Autowired
    MemberService memberService;

    @Override
    public FollowDTO save(FollowDTO dto) {
        Follow entity = mapper.toEntity(dto);
        entity.setMember(memberService.searchEntity(dto.getMemberId()));
        entity.setFollower(memberService.searchEntity(dto.getFollowerId()));
        entity = followRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public FollowDTO delete(long id) {
        return null;
    }

    @Override
    public FollowDTO search(Long id) {
        return null;
    }
}
