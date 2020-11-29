package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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
        return "Auth/SignUp";
    }

    @PostMapping("/signup")
    public RedirectView signupSubmit(@Valid @ModelAttribute User user) {
        var redirectView = new RedirectView();
        try {
            userService.registerNewUser(user);
            redirectView.setUrl("/login");
            return redirectView;
        } catch (UsernameAlreadyExistsException e) {
            redirectView.setUrl("/signup/?error");
            return redirectView;
        }
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/login")
    public String login() {
        return "Auth/Login";
    }

}

