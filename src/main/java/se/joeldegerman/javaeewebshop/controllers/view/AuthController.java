package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.joeldegerman.javaeewebshop.exceptions.UsernameAlreadyExistsException;
import se.joeldegerman.javaeewebshop.models.dto.UserAuthDto;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.services.AuthServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService userService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthServiceImpl userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserAuthDto());
        return "Auth/SignUp";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute UserAuthDto userDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "Auth/SignUp";
        }

        try {
            request.getSession();
            var user = new User(userDto);
            userService.registerNewUser(user);
            var userDetail = userDetailsService.loadUserByUsername(userDto.getUsername());
            var token = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return "redirect:/";
        } catch (UsernameAlreadyExistsException e) {
            return "redirect:/signup?error";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "Auth/Login";
    }

}

