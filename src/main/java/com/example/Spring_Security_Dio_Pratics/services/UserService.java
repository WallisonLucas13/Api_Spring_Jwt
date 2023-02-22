package com.example.Spring_Security_Dio_Pratics.services;

import com.example.Spring_Security_Dio_Pratics.configs.security.jwt.JwtService;
import com.example.Spring_Security_Dio_Pratics.enums.RoleName;
import com.example.Spring_Security_Dio_Pratics.models.AuthenticationResponse;
import com.example.Spring_Security_Dio_Pratics.models.RoleModel;
import com.example.Spring_Security_Dio_Pratics.models.UserModel;
import com.example.Spring_Security_Dio_Pratics.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(UserModel userModel){
        boolean exists = repository.existsByUsername(userModel.getUsername());

        if(exists){
            throw new IllegalArgumentException();
        }

        userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));

        RoleModel role = new RoleModel();
        role.setRoleName(RoleName.ROLE_ADMIN);

        userModel.setRoles(List.of(role));

        repository.save(userModel);
        var token = jwtService.generateToken(userModel);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(UserModel model){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getUsername(),
                        model.getPassword()
                )
        );

        var user = repository.findByUsername(model.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(""));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();

    }
}
