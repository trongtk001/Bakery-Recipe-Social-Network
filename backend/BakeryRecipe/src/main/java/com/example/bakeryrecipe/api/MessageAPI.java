package com.example.bakeryrecipe.api;


import com.example.bakeryrecipe.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("isAuthenticated()")
public class MessageAPI {

    private List<Long> memberList;
    private Map<String, List<Long>> onlineMember;

    public MessageAPI() {
        memberList = new ArrayList<>();
        onlineMember = new HashMap<>();
        onlineMember.put("onlineMember", memberList);
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom")
    public Message login(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public MessageDTO recMessage(@Payload MessageDTO message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverID().toString(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
}