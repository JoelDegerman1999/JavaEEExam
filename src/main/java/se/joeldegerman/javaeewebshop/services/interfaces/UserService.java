package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.security.User;

public interface UserService {
    User registerNewUser(User user) throws IllegalArgumentException;
}
