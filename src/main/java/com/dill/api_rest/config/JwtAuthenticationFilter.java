package com.dill.api_rest.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtTokens jwtTokens;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokens jwtTokens) {
        this.jwtTokens = jwtTokens;
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/token");

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = this.jwtTokens.genereToken(userDetails);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
