package com.example.Spring_Security_Dio_Pratics.repositories;

import com.example.Spring_Security_Dio_Pratics.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);
    Boolean existsByNome(String nome);
    void deleteByNome(String nome);
}
