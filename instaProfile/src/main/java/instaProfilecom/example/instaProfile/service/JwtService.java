package instaProfilecom.example.instaProfile.service;

import instaProfilecom.example.instaProfile.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "this_is_a_very_long_secret_key_for_jwt_signing_123!";

    private SecretKey key() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // 🔐 CREATE TOKEN
    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 20))
                .signWith(key())
                .compact();
    }

    // ✅ VERIFY TOKEN (true / false)
    public boolean verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 📤 TOKEN SE USERNAME NIKALNA
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 📤 TOKEN SE USER ID
//    public Long extractUserId(String token) {
//        return ((Number) Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("id")).longValue();
    }


