package com.example.Spring_Security_Dio_Pratics.controllers;

import com.example.Spring_Security_Dio_Pratics.dtos.ClienteDto;
import com.example.Spring_Security_Dio_Pratics.models.Cliente;
import com.example.Spring_Security_Dio_Pratics.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ClienteController implements ClienteControllerImpl<ClienteDto>{

    @Autowired
    private ClienteService service;

    @Override
    @PostMapping("/Salvar")
    public ResponseEntity<String> save(@RequestBody @Valid ClienteDto clienteDto) {

        service.save(clienteDto);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente Salvo com Sucesso!");
    }

    @Override
    @GetMapping("/Listar/Todos")
    public ResponseEntity<List<ClienteDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @Override
    @GetMapping("/ById/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") Long id) {
        try{

            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findById(id));

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClienteDto(""));
        }
    }

    @Override
    @GetMapping("/ByNome/{nome}")
    public ResponseEntity<ClienteDto> findByNome(@PathVariable("nome") String nome) {
        try{

            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByNome(nome));

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClienteDto(""));
        }
    }

    @Override
    @DeleteMapping("/Delete/ById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        try{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente Apagado com Sucesso!");

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente Inexistente!");
        }
    }

    @Override
    @DeleteMapping("/Delete/ByNome/{nome}")
    public ResponseEntity<String> deleteByNome(@PathVariable("nome") String nome) {
        try{
            service.deleteByNome(nome);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente Apagado com Sucesso!");

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente Inexistente!");
        }
    }

    @Override
    @PutMapping("/Atualizar/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@RequestBody @Valid ClienteDto clienteDto,@PathVariable("id") Long id) {
        try{

            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarById(id, clienteDto));

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClienteDto(""));
        }
    }
}
