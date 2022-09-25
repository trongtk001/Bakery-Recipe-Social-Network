package com.example.bakeryrecipe.service;

public interface Service<T> {

    T save(T dto);

    T edit(T dto);

    T delete(long id);

    T search(Long id);

}
