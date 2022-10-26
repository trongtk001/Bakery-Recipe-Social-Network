package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper extends BaseMapper<Member, MemberDTO> {

    public MemberMapper(ModelMapper modelMapper) {
        super(modelMapper, Member.class, MemberDTO.class);
    }
}
