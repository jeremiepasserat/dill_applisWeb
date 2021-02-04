package com.dill.api_rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication() a tester
        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
    }

   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/*").authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   }
}
