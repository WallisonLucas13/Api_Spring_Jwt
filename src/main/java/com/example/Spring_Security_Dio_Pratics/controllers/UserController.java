package com.example.Spring_Security_Dio_Pratics.controllers;

import com.example.Spring_Security_Dio_Pratics.dtos.UserModelDto;
import com.example.Spring_Security_Dio_Pratics.models.AuthenticationResponse;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserModelDto model){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userService.register(model.transform())
            );
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid UserModelDto model){

        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userService.authenticate(model.transform()));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
