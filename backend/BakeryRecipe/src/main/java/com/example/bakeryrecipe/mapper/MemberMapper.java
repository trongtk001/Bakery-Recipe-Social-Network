package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper extends BaseMapper<Member, MemberDTO> {
    public MemberMapper(ModelMapper modelMapper) {
        super(modelMapper, Member.class, MemberDTO.class);
    }

    public static PropertyMap<Member, MemberDTO> entityToDTOPropertyMap = new PropertyMap<Member, MemberDTO>() {
        @Override
        protected void configure() {
            map(source.getRolesString(), destination.getRoles());
            skip(destination.getPassword());
        }
    };

    public static PropertyMap<MemberDTO, Member> dtoToEntityPropertyMap = new PropertyMap<MemberDTO, Member>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };
}
