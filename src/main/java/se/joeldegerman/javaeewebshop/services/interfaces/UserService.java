package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByName(String username);
}
