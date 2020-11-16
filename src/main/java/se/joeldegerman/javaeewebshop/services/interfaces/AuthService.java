package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.exceptions.UsernameAlreadyExistsException;
import se.joeldegerman.javaeewebshop.models.entity.User;

public interface AuthService {
    User registerNewUser(User user) throws UsernameAlreadyExistsException;
}
