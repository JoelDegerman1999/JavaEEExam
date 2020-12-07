package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;
import se.joeldegerman.javaeewebshop.annotation.PasswordMatches;
import se.joeldegerman.javaeewebshop.models.entity.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {
    @NotBlank(message = "Full name can't be blank")
    private String fullName;

    @NotBlank(message = "Email can't be blank")
    private String username;

    @NotBlank(message = "Password can't be blank")
    private String password;
    private String matchingPassword;

    @NotNull(message = "Address can't be blank")
    private Address address;
}
