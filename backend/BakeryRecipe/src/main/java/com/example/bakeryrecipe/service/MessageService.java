package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.MessageDTO;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.Message;
import com.example.bakeryrecipe.mapper.MessageMapper;
import com.example.bakeryrecipe.repository.MessageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class MessageService implements BaseService<MessageDTO> {

    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final MemberService memberService;

    public MessageService(MessageRepository repository, MessageMapper mapper, MemberService memberService) {
        this.repository = repository;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    private void checkMember(long id) {
        Member member = memberService.searchEntity(id);
        if (isNull(member)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found member");
        }
    }

    private void checkMember(MessageDTO dto){
        checkMember(dto.getSenderID());
        checkMember(dto.getReceiverID());
    }


    @Override
    public MessageDTO save(MessageDTO dto) {
        checkMember(dto);

        Message entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public MessageDTO update(MessageDTO dto) {
        return null;
    }

    @Override
    public MessageDTO delete(MessageDTO dto) {
        return null;
    }

    public List<MessageDTO> getMessage(long memberID, long friendID, Pageable pageable) {
        checkMember(memberID);
        checkMember(friendID);

        List<Message> messages = repository.findAllByMemberSender_IdAndMemberReceiver_IdOrMemberReceiver_IdAndMemberSender_Id(memberID, friendID, pageable);
        return messages.stream().map(message -> mapper.toDTO(message)).collect(Collectors.toList());
    }
}
