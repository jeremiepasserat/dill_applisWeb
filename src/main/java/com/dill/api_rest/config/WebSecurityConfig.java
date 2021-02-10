package com.dill.api_rest.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    //Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .dataSource(dataSource);        }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").authenticated().and().httpBasic();

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(10);
    }
}
