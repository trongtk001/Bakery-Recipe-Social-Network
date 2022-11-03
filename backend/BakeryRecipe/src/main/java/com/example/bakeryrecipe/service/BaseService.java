package com.example.bakeryrecipe.service;

public interface BaseService<T> {

    T save(T dto);

    T update(T dto);

    T delete(long id);
}
