package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import se.joeldegerman.javaeewebshop.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public User() {
    }
}

