package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.LikeDTO;
import com.example.bakeryrecipe.entity.Like;
import com.example.bakeryrecipe.mapper.LikeMapper;
import com.example.bakeryrecipe.repository.LikeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LikeService implements BaseService<LikeDTO>{


    private final LikeRepository likeRepository;

    private final LikeMapper mapper;

    private final PostService postService;

    public LikeService(LikeRepository likeRepository, LikeMapper mapper, PostService postService) {
        this.likeRepository = likeRepository;
        this.mapper = mapper;
        this.postService = postService;
    }

    private void checkPost(long id) {
        if (postService.searchEntity(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
        }
    }

    @Override
    public LikeDTO save(LikeDTO dto) {
        checkPost(dto.getPostID());

        Like like;
        LikeDTO likeDTO = new LikeDTO();
        like = likeRepository.findEmojiByPost_IdAndMember_Id(dto.getPostID(), dto.getMemberID());
        if(like == null){
            like = mapper.toEntity(dto);
            like.setStatus((byte) 1);
            likeDTO.setAction(LikeDTO.LikeAction.LIKE);
        }
        else {
            if (like.getStatus() == 2) {
                like.setStatus((byte) 1);
                likeDTO.setAction(LikeDTO.LikeAction.LIKE);
            } else {
                like.setStatus((byte) 2);
                likeDTO.setAction(LikeDTO.LikeAction.UNLIKE);
            }
        }
        like = likeRepository.save(like);
        mapper.toDTO(like, likeDTO);
        return likeDTO;
    }

    @Override
    public LikeDTO update(LikeDTO dto) {
        return null;
    }

    public List<LikeDTO> findAllByPost(Long id){
        List<Like> list = likeRepository.findAllByPost_Id(id);
        return mapper.toDTOList(list);
    }

    public LikeDTO delete(long id) {
        return null;
    }

    public LikeDTO search(Long id) {
        return null;
    }
}
