package com.codearti.seguridad.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtServicio {

    @Value("${app.jwt.secreto}")
    private String secreto;

    @Value("${app.jwt.expiracion}")
    private long expiracionMs;

    public String generarToken(UserDetails usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + expiracionMs);
        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(obtenerClave())
                .compact();
    }

    public String extraerNombreUsuario(String token) {
        return extraerReclamo(token, Claims::getSubject);
    }

    public boolean esTokenValido(String token, UserDetails usuario) {
        final String nombre = extraerNombreUsuario(token);
        return nombre.equals(usuario.getUsername()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return extraerReclamo(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extraerReclamo(String token, Function<Claims, T> resolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(obtenerClave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }

    private SecretKey obtenerClave() {
        byte[] bytes = Decoders.BASE64.decode(secreto);
        return Keys.hmacShaKeyFor(bytes);
    }
}