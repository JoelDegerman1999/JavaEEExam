package se.joeldegerman.javaeewebshop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUtil(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    public String generateToken(CustomUserDetail userDetail) {
        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .claim("authorities", userDetail.getAuthorities())
                .claim("name", userDetail.getFullName())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(
                getUsername(token),
                null,
                createSimpleGrantedAuthoritySet(token)
        );
    }

    private Claims getClaimsBody(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey)
                .build().parseClaimsJws(token);
        return claimsJws.getBody();
    }

    private Set<SimpleGrantedAuthority> createSimpleGrantedAuthoritySet(String token) {
        var body = getClaimsBody(token);
        var authorities = (List<Map<String, String>>) body.get("authorities");

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());

        return simpleGrantedAuthorities;
    }

    private String getUsername(String token) {
        var body = getClaimsBody(token);
        return body.getSubject();
    }

}
