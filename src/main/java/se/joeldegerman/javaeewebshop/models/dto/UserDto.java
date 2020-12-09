package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.entity.Address;
import se.joeldegerman.javaeewebshop.security.EUserRole;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private String fullName;
    private String username;
    private Address address;
}
