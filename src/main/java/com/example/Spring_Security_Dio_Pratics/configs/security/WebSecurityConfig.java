package com.example.Spring_Security_Dio_Pratics.configs.security;

import com.example.Spring_Security_Dio_Pratics.enums.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/User/New")
                .anonymous()
                .requestMatchers(HttpMethod.POST, "/Salvar")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/Delete/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();

        return http.build();

    }
}
