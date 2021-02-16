package com.dill.api_rest.config;

import com.dill.api_rest.exception.TokenErroneException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokens {

    // une semaine
    private static final long EXPIRATION_TIME = 659_000_000;
    public static final String BEARER = "Bearer ";

    @Autowired
    SecretKey secretKey;

    public String genereToken(UserDetails userDetails){
        String login = userDetails.getPassword();
        var roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", roles);
        String token = Jwts.builder().setClaims(claims).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey).compact();

        return token;
    }

    public UsernamePasswordAuthenticationToken decodeToken(String token) throws TokenErroneException {

        // permet de supprimer l'entête pour analyser juste le token
        if (token.startsWith(BEARER)){
            token = token.replaceFirst(BEARER, "");

        }

        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            String login = jwsClaims.getBody().getSubject();
            List<String> roles = jwsClaims.getBody().get("roles", List.class);

            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login, null, authorities);

            return authentication;
        } catch (JwtException exception){
            throw new TokenErroneException();
        }
    }
}
