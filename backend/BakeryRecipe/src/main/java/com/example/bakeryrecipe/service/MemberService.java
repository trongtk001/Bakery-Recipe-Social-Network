package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.MemberDTO;
import org.springframework.http.ResponseCookie;

public interface MemberService extends Service<MemberDTO>{

    ResponseCookie Login(String username, String password);

}
