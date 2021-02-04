package com.dill.api_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiRestApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String password = "admin";
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("sout : " + encodedPassword);

        System.out.println(passwordEncoder.matches("admin", encodedPassword));
    }

}
