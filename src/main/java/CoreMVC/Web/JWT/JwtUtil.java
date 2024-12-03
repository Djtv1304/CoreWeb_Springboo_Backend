package CoreMVC.Web.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    // Clave secreta para firmar el token
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    // Método para obtener la clave secreta para firmar el token
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userId) {
        // Obtengo la clave secreta para firmar el token
        SecretKey key = getSigningKey();
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de expiración
                .signWith(key)
                .compact();
    }

    public String extractUserId(String token) {
        SecretKey key = getSigningKey();
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (JwtException e) {
            // No confiar en el JWT si la verificación falla
            throw new IllegalArgumentException("Token JWT no confiable", e);
        }

    }

    public String refineJwtToken(String token) {
        return token.replace("Bearer ", "").trim();
    }
}

