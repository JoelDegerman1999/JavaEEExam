package se.joeldegerman.javaeewebshop.security.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {
    private final JwtConfig config;

    public JwtSecretKey(JwtConfig config) {
        this.config = config;
    }

    @Bean
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(config.getSecret().getBytes());
    }
}
