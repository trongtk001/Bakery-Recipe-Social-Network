package com.example.bakeryrecipe.service.mailservice;

import com.example.bakeryrecipe.dto.DataMailDTO;
import com.example.bakeryrecipe.dto.MemberDTO;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService  {
    private final MailService mailService;

    public ClientService(MailService mailService) {
        this.mailService = mailService;
    }

    public void create(MemberDTO dto,String code) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(dto.getEmail());
            dataMail.setSubject("XÁC NHẬN TẠO MỚI THÔNG TIN NGƯỜI DÙNG");

            Map<String, Object> props = new HashMap<>();
            props.put("name", dto.getName());
            props.put("username", dto.getUsername());
            props.put("code",code);
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, "index");

        } catch (MessagingException exp){
            exp.printStackTrace();
        }
    }
}
