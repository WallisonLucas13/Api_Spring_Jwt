package com.example.Spring_Security_Dio_Pratics.controllers;

import com.example.Spring_Security_Dio_Pratics.dtos.UserModelDto;
import com.example.Spring_Security_Dio_Pratics.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/New")
    public ResponseEntity<String> save(@RequestBody @Valid UserModelDto model){

        try{
            userService.save(model.transform());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário Criado com Sucesso!");
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Esse username já está em uso, tente novamente!");
        }
    }
}
