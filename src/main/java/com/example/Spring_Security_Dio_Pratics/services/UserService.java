package com.example.Spring_Security_Dio_Pratics.services;

import com.example.Spring_Security_Dio_Pratics.enums.RoleName;
import com.example.Spring_Security_Dio_Pratics.models.RoleModel;
import com.example.Spring_Security_Dio_Pratics.models.UserModel;
import com.example.Spring_Security_Dio_Pratics.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public void save(UserModel model){

        model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        RoleModel role = new RoleModel();
        role.setRoleName(RoleName.ROLE_USER);

        model.setRoles(List.of(role));
        repository.save(model);
    }
}
