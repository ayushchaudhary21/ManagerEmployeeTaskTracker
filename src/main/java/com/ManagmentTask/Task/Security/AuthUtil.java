package com.ManagmentTask.Task.Security;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey; //->secret key

    private SecretKey convertKey()
    {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)); //-> header
    }


    // GENERATE TOKEN — called after successful login
    public String generateToken(EmployeeEntity employee) {
        return Jwts.builder()
                .setSubject(employee.getUserName())          // who this token belongs to
                .claim("userId", employee.getEmployeeId()) // extra data you want to store
                .setIssuedAt(new Date())               // when token was created
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // expires in 24hrs
                .signWith(convertKey())                    // sign with secret key
                .compact();                                // build it into a String

    }

    // Validate and get the userName from user.

    public String getUserNameFromToken(String token)
    {
        // EXTRACT USERNAME
        return Jwts.parserBuilder()
                .setSigningKey(convertKey())
                .build()
                .parseClaimsJws(token)
                .getBody()                      // 0.11.5 uses getBody()
                .getSubject();
    }

}
