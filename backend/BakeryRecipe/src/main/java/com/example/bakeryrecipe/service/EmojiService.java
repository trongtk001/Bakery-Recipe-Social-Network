package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.EmojiDTO;
import com.example.bakeryrecipe.entity.Emoji;
import com.example.bakeryrecipe.mapper.EmojiMapper;
import com.example.bakeryrecipe.repository.EmojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmojiService implements BaseService<EmojiDTO>{

    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    EmojiMapper mapper;

    @Override
    public EmojiDTO save(EmojiDTO dto) {
        Emoji emoji;
        emoji = emojiRepository.findEmojiByPost_IdAndMember_Id(dto.getPost().getId(),dto.getMember().getId());
        if(emoji == null){
            emoji = mapper.toEntity(dto);
            emoji = emojiRepository.save(emoji);
            return mapper.toDTO(emoji);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT,"member liked this post");
    }

    public List<EmojiDTO> findAllByPost(Long id){
        List<Emoji> list = emojiRepository.findAllByPost_Id(id);
        return mapper.toDTOList(list);
    }
    @Override
    public EmojiDTO delete(long id) {
        return null;
    }

    @Override
    public EmojiDTO search(Long id) {
        return null;
    }
}
