package com.example.Spring_Security_Dio_Pratics.controllers;

import com.example.Spring_Security_Dio_Pratics.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteControllerImpl<T> {

    ResponseEntity<String> save(T t);

    ResponseEntity<List<T>> listAll();

    ResponseEntity<T> findById(Long id);

    ResponseEntity<T> findByNome(String nome);

    ResponseEntity<String> deleteById(Long id);

    ResponseEntity<String> deleteByNome(String nome);

    ResponseEntity<ClienteDto> atualizarCliente(T t, Long id);

}
