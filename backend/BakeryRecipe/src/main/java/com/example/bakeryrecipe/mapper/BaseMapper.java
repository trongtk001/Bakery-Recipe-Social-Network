package com.example.bakeryrecipe.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class BaseMapper<T, K> {

    private final ModelMapper modelMapper;
    private final Type entityType;
    private final Type dtoType;

    public BaseMapper(ModelMapper modelMapper, Type entityType, Type dtoType) {
        this.modelMapper = modelMapper;
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public K toDTO(T entity) {
        return (K) modelMapper.map(entity, dtoType);
    }

    public void toDTO(T entity, K dto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(entity, dto);
    }

    public T toEntity(K dto) {
        return (T) modelMapper.map(dto, entityType);
    }

    public void toEntity(K dto, T entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
    }

    public List<K> toDTOList(List<T> entities) {
        return entities.stream().map(t -> toDTO(t)).collect(Collectors.toList());
    }

    public List<T> toEntityList(List<K> dtos) {
        return dtos.stream().map(k -> toEntity(k)).collect(Collectors.toList());
    }
}
