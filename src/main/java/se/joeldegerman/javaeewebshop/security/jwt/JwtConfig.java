package se.joeldegerman.javaeewebshop.security.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secret;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    public JwtConfig() {
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
