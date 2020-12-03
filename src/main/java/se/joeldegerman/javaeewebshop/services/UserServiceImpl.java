package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByName(String username) {
        return userRepository.findByUsername(username);
    }
}
