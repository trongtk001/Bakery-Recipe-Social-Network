package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.MemberDTO;
import com.example.bakeryrecipe.entity.Member;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper implements Mapper<Member, MemberDTO> {

    private final ModelMapper modelMapper;


    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(entityToDTOPropertyMap);
        modelMapper.addMappings(dtoToEntityPropertyMap);
    }

    PropertyMap<Member, MemberDTO> entityToDTOPropertyMap = new PropertyMap<Member, MemberDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };

    PropertyMap<MemberDTO, Member> dtoToEntityPropertyMap = new PropertyMap<MemberDTO, Member>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };

    @Override
    public Member toEntity(MemberDTO dto) {
        return modelMapper.map(dto, Member.class);
    }

    @Override
    public Member toEntity(MemberDTO dto, Member entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public MemberDTO toDTO(Member entity) {
        return modelMapper.map(entity, MemberDTO.class);
    }

    @Override
    public MemberDTO toDTO(Member entity, MemberDTO dto) {
        return null;
    }

    @Override
    public List<Member> toEntityList(List<MemberDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> toDTOList(List<Member> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

}
