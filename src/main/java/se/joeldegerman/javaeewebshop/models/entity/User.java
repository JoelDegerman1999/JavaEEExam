package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.dto.UserAuthDto;
import se.joeldegerman.javaeewebshop.security.EUserRole;

import javax.persistence.*;

@Entity
@Table(name = "my_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String username;

    private String password;

    private boolean isActive = true;

    @Enumerated(EnumType.ORDINAL)
    private EUserRole userRole;

    @Embedded
    private Address address;

    public User() {
    }

    public User(UserAuthDto userDto) {
        this.fullName = userDto.getFullName();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.address = userDto.getAddress();
    }
}

