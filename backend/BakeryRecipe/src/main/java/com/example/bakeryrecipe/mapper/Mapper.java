package com.example.bakeryrecipe.mapper;


import java.util.List;

public interface Mapper<T, K> {

    public T toEntity(K dto);

    public T toEntity(K dto, T entity);

    public K toDTO(T entity);

    public K toDTO(T entity, K dto);

    public List<T> toEntityList(List<K> dtos);

    public List<K> toDTOList(List<T> entities);

}
