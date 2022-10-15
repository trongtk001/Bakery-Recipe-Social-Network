package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        Follow entity;
        entity = followRepository.findFollowByMember_IdAndFollower_Id(dto.getMember().getId(), dto.getFollower().getId());
        if(entity == null){
            entity = mapper.toEntity(dto);
            entity = followRepository.save(entity);
            return mapper.toDTO(entity);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"only follow 1 person at a time");
    }

    public List<FollowDTO> findAllFriend(Long id){
        List<Follow> listFriend = followRepository.findAllByFollower_Id(id);
        return mapper.toDTOList(listFriend);
    }

    public List<FollowDTO> findAllFollower(Long id) {
        List<Follow> followList = followRepository.findAllByMember_Id(id);
        return mapper.toDTOList(followList);
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
