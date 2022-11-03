package com.example.bakeryrecipe.api;


import com.example.bakeryrecipe.api.input.GetMessageInput;
import com.example.bakeryrecipe.api.output.ListMessageOutput;
import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.dto.MessageDTO;
import com.example.bakeryrecipe.repository.ParticipantRepository;
import com.example.bakeryrecipe.service.MemberService;
import com.example.bakeryrecipe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("message")
public class MessageAPI {

    private final ParticipantRepository participantRepository;

    private final MemberService memberService;

    private final MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageAPI(ParticipantRepository participantRepository, MemberService memberService, MessageService messageService) {
        this.participantRepository = participantRepository;
        this.memberService = memberService;
        this.messageService = messageService;
    }

    @SubscribeMapping("/chat/online")
    public void login() {
        List<String> onlineUser = participantRepository.getOnlineUser();
        simpMessagingTemplate.convertAndSend("/app/chat/online", onlineUser);
    }



    @MessageMapping("/private-message")
    public void sendMessage(@Payload MessageDTO message, Principal principal) {
        try {
            MemberDTO member = memberService.searchMemberByUsername(principal.getName());
            if (member.getId().equals(message.getSenderID())) {
                String receiverName = memberService.search(message.getReceiverID()).getUsername();

                message.setCreateDate(Instant.now());
                MessageDTO messageDTO = messageService.save(message);
                simpMessagingTemplate.convertAndSendToUser(receiverName, "queue/private", messageDTO);
            } else {
                throw new RuntimeException("Forbidden");
            }
        } catch (Exception ex) {
            simpMessagingTemplate.convertAndSendToUser(principal.getName(), "info/error", new HashMap<String, Object>() {{
                put("errorMessage", ex.getMessage());
                put("payload", message);
            }});
        }
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ListMessageOutput getMessage(@RequestBody GetMessageInput getMessageInput, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createDate"));
        List<MessageDTO> messageDTOS = messageService.getMessage(getMessageInput.getMemberID(), getMessageInput.getFriendID(), pageable);
        return new ListMessageOutput(page, messageDTOS.size(), 0, messageDTOS);
    }
}