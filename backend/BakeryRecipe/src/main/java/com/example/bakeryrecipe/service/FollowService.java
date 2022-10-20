package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService implements BaseService<FollowDTO>{

    private final FollowRepository followRepository;
    private final FollowMapper mapper;
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    public FollowService(FollowRepository followRepository, FollowMapper mapper, MemberMapper memberMapper, MemberService memberService) {
        this.followRepository = followRepository;
        this.mapper = mapper;
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    private void checkMember(long id) {
        Member member = memberService.searchEntity(id);
        if (null == member) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found member");
        }
    }

    private void checkMember(FollowDTO dto){
        Member member = memberService.searchEntity(dto.getMember().getId());
        Member follower = memberService.searchEntity(dto.getFollower().getId());
        if (null == member || null == follower) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found member");
        }
    }

    @Override
    public FollowDTO save(FollowDTO dto) {
        Follow entity;
        checkMember(dto);
        entity = followRepository.findFollowByMember_IdAndFollower_Id(dto.getMember().getId(), dto.getFollower().getId());
        if(entity == null){
            entity = mapper.toEntity(dto);
            entity = followRepository.save(entity);
            return mapper.toDTO(entity);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Already followed");
    }

    public List<FollowDTO> findAllFollowByFollower(long id){
        checkMember(id);
        List<Follow> followList = followRepository.findAllByFollower_Id(id);
        return mapper.toDTOList(followList);
    }

    public List<FollowDTO> findAllFollowByMember(long id) {
        checkMember(id);
        List<Follow> followList = followRepository.findAllByMember_Id(id);
        return mapper.toDTOList(followList);
    }

    public List<MemberDTO> findAllFriend(long id) {
        checkMember(id);
        List<Follow> followList = followRepository.findAllByFollower_Id(id);
        List<Member> friendList = followList.stream().map(follow -> follow.getMember()).collect(Collectors.toList());
        return memberMapper.toDTOList(friendList);
    }

    public List<MemberDTO> findAllFollower(long id) {
        checkMember(id);
        List<Follow> followList = followRepository.findAllByMember_Id(id);
        List<Member> followerList = followList.stream().map(follow -> follow.getFollower()).collect(Collectors.toList());
        return memberMapper.toDTOList(followerList);
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
