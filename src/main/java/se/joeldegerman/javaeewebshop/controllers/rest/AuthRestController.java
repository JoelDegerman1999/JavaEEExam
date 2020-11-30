package se.joeldegerman.javaeewebshop.controllers.rest;

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
import se.joeldegerman.javaeewebshop.security.jwt.JwtUtil;

@RestController
@RequestMapping("/api/")
public class AuthRestController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthRestController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("auth")
    public ResponseEntity login(@RequestBody AuthenticationLoginRequest authenticationLoginRequest) {

        try {
            auth(authenticationLoginRequest.getUsername(), authenticationLoginRequest.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Wrong username and/or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationLoginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void auth(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }
}
