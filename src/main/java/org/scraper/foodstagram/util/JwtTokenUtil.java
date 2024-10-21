package org.scraper.foodstagram.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import java.util.Date;

public class JwtTokenUtil {

    private static final String signedKey = "SlNPTiBXZWIgVG9rZW5zIGFyZSBKU09OIG9iamVjdHMgdXNlZCB0byBzZW5kIGluZm9ybWF0aW9uIGJldHdlZW4gcGFydGllcyBpbiBhIGNvbXBhY3QgYW5kIHNlY3VyZSBtYW5uZXIu";

    public static String generateEmailActivationToken(String email) throws WeakKeyException {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(expireAfter15Minutes())
                .signWith(Keys.hmacShaKeyFor(signedKey.getBytes()))
                .compact();
    }

    public static String extractEmailFieldFromEmailActivationToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(signedKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static Date extractExpirationFieldFromEmailActivationToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(signedKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    private static Date expireAfter15Minutes() {
        return new Date(System.currentTimeMillis() + 15 * 60 * 1000);
    }
}
