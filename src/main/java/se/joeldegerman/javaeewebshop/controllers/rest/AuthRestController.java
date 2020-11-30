package se.joeldegerman.javaeewebshop.controllers.rest;

import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.dto.jwt.AuthenticationLoginRequest;
import se.joeldegerman.javaeewebshop.models.dto.jwt.AuthenticationResponse;
import se.joeldegerman.javaeewebshop.security.jwt.JwtConfig;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/")
public class AuthRestController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public AuthRestController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @PostMapping("auth")
    public ResponseEntity login(@RequestBody AuthenticationLoginRequest authenticationLoginRequest) {

        try {
            auth(authenticationLoginRequest.getUsername(), authenticationLoginRequest.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Wrong username and/or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationLoginRequest.getUsername());

        String secret = "secretsecretsecretsecretsecretsecret";

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(5)))
                .signWith(secretKey)
                .compact();

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void auth(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException exception) {
            throw new RuntimeException();
        }
    }
}
