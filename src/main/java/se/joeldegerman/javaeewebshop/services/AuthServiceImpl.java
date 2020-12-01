package se.joeldegerman.javaeewebshop.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.exceptions.UsernameAlreadyExistsException;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.security.EUserRole;
import se.joeldegerman.javaeewebshop.services.interfaces.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User registerNewUser(User user) throws UsernameAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent())  {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(EUserRole.CUSTOMER);
        return userRepository.save(user);
    }
}
