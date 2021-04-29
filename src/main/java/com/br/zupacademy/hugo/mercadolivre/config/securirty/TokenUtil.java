package com.br.zupacademy.hugo.mercadolivre.config.securirty;

import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    private Date currentDate = new Date();

    public String gerarToken(Authentication auth){
        Usuario usuario = (Usuario) auth.getPrincipal();
        return Jwts.builder()
                .setIssuer("Token de Login")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + Long.parseLong(jwtExpiration)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
