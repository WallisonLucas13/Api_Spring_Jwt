package com.example.Spring_Security_Dio_Pratics.configs.security;

import com.example.Spring_Security_Dio_Pratics.models.UserModel;
import com.example.Spring_Security_Dio_Pratics.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel model = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais Inválidas!"));

        return new User(model.getUsername(), model.getPassword()
                , true, true, true, true, model.getAuthorities());
    }
}
