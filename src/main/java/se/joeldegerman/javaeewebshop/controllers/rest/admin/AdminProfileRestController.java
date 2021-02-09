package se.joeldegerman.javaeewebshop.controllers.rest.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("api/admin/profile")
public class AdminProfileRestController {

    private final UserRepository userRepository;

    public AdminProfileRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User getProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userRepository.findByUsername(String.valueOf(principal));
        return optionalUser.orElse(null);
    }
}
