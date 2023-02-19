package com.example.Spring_Security_Dio_Pratics.services;

import com.example.Spring_Security_Dio_Pratics.dtos.ClienteDto;

import java.util.List;

public interface ClienteServiceImpl<T> {

    void save(T t);

    List<T> listAll();

    T findById(Long id) throws IllegalArgumentException;

    T findByNome(String nome) throws IllegalArgumentException;

    void deleteById(Long id) throws IllegalArgumentException;

    void deleteByNome(String nome) throws IllegalArgumentException;

    ClienteDto atualizarById(Long id, T t) throws IllegalArgumentException;
}
