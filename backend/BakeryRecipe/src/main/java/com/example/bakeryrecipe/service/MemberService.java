package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.MemberDTO;

public interface MemberService extends Service<MemberDTO>{

    MemberDTO searchMemberByID(Long id);
}
