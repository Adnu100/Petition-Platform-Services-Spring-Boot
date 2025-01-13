package org.sslp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JWTUtils {

    private final Key secretKey;
    private final SimpleDateFormat formatter;

    public JWTUtils(@Value("${secret.key}") String secretKeyString) {
        this.secretKey = new SecretKeySpec(secretKeyString.getBytes(), "HmacSHA256");
        this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    }

    public String createJWT(String userName, String userType) {
        return createJWT(userName, userType, System.currentTimeMillis());
    }

    public String createJWT(String userName, String userType, long nowMillis) {
        return Jwts.builder()
            .subject(userName)
            .issuedAt(new Date(nowMillis))
            .expiration(new Date(nowMillis + 3600000))
            .claims(Map.of(
                "username", userName,
                "type", userType,
                "expiry", formatter.format(new Date(nowMillis + 3600000))
            ))
            .signWith(secretKey)
            .compact();
    }

    public Claims isTokenValid(String token) {
        return isTokenValid(token, false);
    }

    public Claims isTokenValid(String token, boolean onlyAdminAllowed) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey((SecretKey) secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload();
            return (onlyAdminAllowed && !claims.get("type").equals("admin")) ?
                null : (!claims.getExpiration().before(new Date()) ? claims : null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
