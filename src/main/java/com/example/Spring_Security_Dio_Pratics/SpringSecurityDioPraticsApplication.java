package com.example.Spring_Security_Dio_Pratics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityDioPraticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDioPraticsApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
