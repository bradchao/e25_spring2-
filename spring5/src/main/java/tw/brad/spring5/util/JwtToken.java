package tw.brad.spring5.util;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtToken {
    private static final String SECRET = "BradChao12345677654321abcdefgCatDog";
    private static final long EXP_TIME = 60*60*1000;
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String createToken(String subject){
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
                .signWith(key)
                .compact();
        return token;
    }

    public static String parseToken(String token){
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        String subject = parser.parseClaimsJws(token).getBody().getSubject();
        return subject;
    }
}
