package com.example.Spring_Security_Dio_Pratics.dtos;

import com.example.Spring_Security_Dio_Pratics.models.Cliente;
import com.example.Spring_Security_Dio_Pratics.models.ClienteImpl;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto implements ClienteImpl<Cliente> {

    public ClienteDto(String nome){
        this.nome = nome;
    }

    @NotBlank
    private String nome;

    @Override
    public Cliente transform() {
        return new Cliente(this.nome);
    }
}
