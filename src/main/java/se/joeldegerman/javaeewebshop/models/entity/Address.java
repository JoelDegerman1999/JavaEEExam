package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
