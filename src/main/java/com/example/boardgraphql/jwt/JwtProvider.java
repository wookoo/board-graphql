package com.example.boardgraphql.jwt;

import com.example.boardgraphql.member.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {


    private final long TOKEN_EXPIRE_TIME;
    private final SecretKey TOKEN_SECRET_KEY;
    private final String ROLE_PREFIX;

    public JwtProvider(@Value("${spring.jwt.expTime}") long tokenExpireTime, @Value("${spring.jwt.secret}") String jwtSecret) {
        this.TOKEN_EXPIRE_TIME = tokenExpireTime;
        this.TOKEN_SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        this.ROLE_PREFIX = "ROLE";
    }


    public String createToken(JwtMemberInfo jwtMemberInfo) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(jwtMemberInfo.getId() + "")
                .claim(ROLE_PREFIX, jwtMemberInfo.getRole())
                .issuedAt(new Date())
                .expiration(new Date(now + TOKEN_EXPIRE_TIME))
                .signWith(TOKEN_SECRET_KEY)
                .compact();
    }

    public JwtMemberInfo getJwtMemberinfo(String token) {
        Claims claims = Jwts.parser().verifyWith(TOKEN_SECRET_KEY).build().parseSignedClaims(token).getPayload();
        long id = Long.parseLong(claims.getSubject());
        Role role = Role.valueOf((String) claims.get(ROLE_PREFIX));
        return JwtMemberInfo.builder().id(id).role(role).build();
    }


    public boolean isValidToken(String token) {

        try {
            Jwts.parser().verifyWith(TOKEN_SECRET_KEY).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException ignored) {
        }
        return false;
    }
}
