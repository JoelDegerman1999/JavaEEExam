package se.joeldegerman.javaeewebshop.services.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;

import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceJpaImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceJpaImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return optionalUser.map(CustomUserDetail::new).get();
    }
}
