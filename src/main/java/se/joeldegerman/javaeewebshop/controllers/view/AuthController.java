package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.exceptions.UsernameAlreadyExistsException;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.services.AuthServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.AuthService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService userService;

    public AuthController(AuthServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "auth/SignUp";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute User user) {
        try {
            userService.registerNewUser(user);
            return "redirect:/login";
        } catch (UsernameAlreadyExistsException e) {
            return "redirect:/signup?error";
        }
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/login")
    public String login() {
        return "auth/Login";
    }

}

