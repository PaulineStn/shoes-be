package shoes.pauline.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Clé secrète pour signer les tokens (Change-la pour une clé plus complexe en production)
    private final String SECRET_KEY = "secret123";

    // Générer un token JWT
    public String generateToken(String usernameOrEmail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, usernameOrEmail);
    }

    // Créer le token avec les claims et le sujet (username ou email)
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 heures de validité
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Récupérer le sujet (username ou email) depuis le token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Récupérer une information spécifique depuis le token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Récupérer tous les claims (données) du token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Vérifier si le token est encore valide
    public boolean isTokenValid(String token, String usernameOrEmail) {
        final String username = extractUsername(token);
        return (username.equals(usernameOrEmail) && !isTokenExpired(token));
    }

    // Vérifier si le token a expiré
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Récupérer la date d'expiration du token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
