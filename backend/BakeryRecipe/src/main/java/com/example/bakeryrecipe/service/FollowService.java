package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.FollowDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Follow;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.mapper.FollowMapper;
import com.example.bakeryrecipe.mapper.MemberMapper;
import com.example.bakeryrecipe.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        Member member = memberService.searchEntity(dto.getMemberID());
        Member follower = memberService.searchEntity(dto.getFollowerID());
        if (null == member || null == follower) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found member");
        }
    }

    @Override
    public FollowDTO save(FollowDTO dto) {
        checkMember(dto);

        Follow follow;
        FollowDTO newFollowDTO = new FollowDTO();

        follow = followRepository.findFollowByMember_IdAndFollower_Id(dto.getMemberID(), dto.getFollowerID());
        if(follow == null){
            follow = mapper.toEntity(dto);
            follow.setStatus(1);
            newFollowDTO.setAction(FollowDTO.FollowAction.FOLLOW);
        } else {
            if (follow.getStatus() == 2) {
                follow.setStatus(1);
                newFollowDTO.setAction(FollowDTO.FollowAction.FOLLOW);
            } else {
                follow.setStatus(2);
                newFollowDTO.setAction(FollowDTO.FollowAction.UNFOLLOW);
            }
        }
        follow = followRepository.save(follow);
        mapper.toDTO(follow, newFollowDTO);
        return  newFollowDTO;
    }

    @Override
    public FollowDTO update(FollowDTO dto) {
        return null;
    }

    @Override
    public FollowDTO delete(FollowDTO dto) {
        return null;
    }

    public List<FollowDTO> findAllFollowByMember(long id) {
        checkMember(id);
        List<Follow> followList = followRepository.findAllByMember_Id(id);
        return mapper.toDTOList(followList);
    }

    public Page<MemberDTO> findAllFriend(long id, Pageable pageable) {
        checkMember(id);
        Page<Follow> followList = followRepository.findAllByFollower_Id(id, pageable);
        List<Member> friendList = followList.getContent().stream().map(follow -> follow.getMember()).collect(Collectors.toList());
        return new PageImpl<>(memberMapper.toDTOList(friendList), pageable, followList.getTotalElements());
    }

    public Page<MemberDTO> findAllFollower(long id, Pageable pageable) {
        checkMember(id);
        Page<Follow> followList = followRepository.findAllByMember_Id(id, pageable);
        List<Member> followerList = followList.stream().map(follow -> follow.getFollower()).collect(Collectors.toList());
        return new PageImpl<>(memberMapper.toDTOList(followerList), pageable, followList.getTotalElements());
    }

    public FollowDTO delete(long id) {
        return null;
    }

    public FollowDTO search(Long id) {
        return null;
    }
}
