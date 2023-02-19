package com.example.Spring_Security_Dio_Pratics.models;

import com.example.Spring_Security_Dio_Pratics.dtos.ClienteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cliente implements ClienteImpl<ClienteDto>{

    public Cliente(String nome){
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Override
    public ClienteDto transform() {
        return new ClienteDto(this.nome);
    }
}
