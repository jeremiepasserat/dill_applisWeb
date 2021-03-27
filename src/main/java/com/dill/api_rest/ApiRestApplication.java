package com.dill.api_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import java.time.LocalDate;

import static org.springframework.orm.hibernate5.SessionFactoryUtils.getDataSource;

@SpringBootApplication
public class ApiRestApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {

        //PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder(10);

        //System.out.println(passwordEncoder.encode("fromage"));


          SpringApplication.run(ApiRestApplication.class, args);

    }

}
