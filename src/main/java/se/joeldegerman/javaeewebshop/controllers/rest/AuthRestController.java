package se.joeldegerman.javaeewebshop.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.joeldegerman.javaeewebshop.models.dto.UserAuthDto;
import se.joeldegerman.javaeewebshop.models.dto.UserDto;
import se.joeldegerman.javaeewebshop.models.dto.jwt.AuthenticationLoginRequest;
import se.joeldegerman.javaeewebshop.models.dto.jwt.AuthenticationResponse;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.security.jwt.JwtUtil;
import se.joeldegerman.javaeewebshop.services.interfaces.AuthService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth/")
public class AuthRestController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public AuthRestController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, AuthService authService, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationLoginRequest authenticationLoginRequest) {
        try {
            auth(authenticationLoginRequest.getUsername(), authenticationLoginRequest.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Wrong username and/or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationLoginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));

    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserAuthDto userAuthDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error, try again");
        }
        authService.registerNewUser(modelMapper.map(userAuthDto, User.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(userAuthDto, UserDto.class));
    }

    private void auth(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }
}
