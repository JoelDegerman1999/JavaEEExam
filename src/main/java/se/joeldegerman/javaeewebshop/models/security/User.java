package se.joeldegerman.javaeewebshop.models.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private boolean isActive;
    private String roles;

    public User() {
    }
}
