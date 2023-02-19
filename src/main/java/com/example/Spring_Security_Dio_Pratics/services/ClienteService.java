package com.example.Spring_Security_Dio_Pratics.services;

import com.example.Spring_Security_Dio_Pratics.dtos.ClienteDto;
import com.example.Spring_Security_Dio_Pratics.models.Cliente;
import com.example.Spring_Security_Dio_Pratics.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements ClienteServiceImpl<ClienteDto> {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional
    public void save(ClienteDto clienteDto) {
        repository.save(clienteDto.transform());
    }

    @Override
    @Transactional
    public List<ClienteDto> listAll() {

        List<ClienteDto> dtos = repository.findAll()
                .stream()
                .map(cliente -> cliente.transform())
                .collect(Collectors.toList());

        return dtos;
    }

    @Override
    @Transactional
    public ClienteDto findById(Long id) throws IllegalArgumentException{

        Optional<Cliente> clienteOpt = repository.findById(id);

        return clienteOpt.orElseThrow(() -> new IllegalArgumentException("")).transform();
    }

    @Override
    @Transactional
    public ClienteDto findByNome(String nome) throws IllegalArgumentException{

        Optional<Cliente> clienteOpt = repository.findByNome(nome);

        return clienteOpt.orElseThrow(() -> new IllegalArgumentException("")).transform();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws IllegalArgumentException{

        boolean exist = repository.existsById(id);

        if(exist){
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("");

    }

    @Override
    @Transactional
    public void deleteByNome(String nome) {
        boolean exist = repository.existsByNome(nome);

        if(exist){
            repository.deleteByNome(nome);
            return;
        }
        throw new IllegalArgumentException("");

    }

    @Override
    @Transactional
    public ClienteDto atualizarById(Long id, ClienteDto clienteDto) throws IllegalArgumentException {

        Optional<Cliente> clienteOpt = repository.findById(id);
        Cliente cliente = clienteDto.transform();

        cliente.setId(clienteOpt.orElseThrow(() -> new IllegalArgumentException(""))
                .getId());

        repository.save(cliente);
        return cliente.transform();
    }
}
