package com.example.bakeryrecipe.service;

public interface BaseService<T> {

    T save(T dto);

    T delete(long id);

    T search(Long id);

}
