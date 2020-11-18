package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "my_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Full name cant be blank")
    private String fullName;

    @NotBlank(message = "Email cant be blank")
    private String username;

    @NotBlank(message = "Password cant be blank")
    private String password;

    private boolean isActive = true;
    private String roles = "ROLE_USER";

    @Embedded
    private Address address;

    public User() {
    }
}

