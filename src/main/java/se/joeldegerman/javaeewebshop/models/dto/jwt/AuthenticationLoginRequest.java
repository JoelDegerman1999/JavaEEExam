package se.joeldegerman.javaeewebshop.models.dto.jwt;

import lombok.Data;

@Data
public class AuthenticationLoginRequest {
    private String username;
    private String password;
}
