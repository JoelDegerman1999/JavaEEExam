package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.joeldegerman.javaeewebshop.models.security.User;
import se.joeldegerman.javaeewebshop.services.UserServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.UserService;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "auth/SignUp";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user) {
        userService.registerNewUser(user);
        return "redirect:/login";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/login")
    public String login() {
        return "auth/Login";
    }


}

