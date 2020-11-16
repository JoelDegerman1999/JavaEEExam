package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.joeldegerman.javaeewebshop.helpers.UserHelper;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.services.OrderServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class OrderController {

    final OrderService orderService;
    final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderServiceImpl orderService, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/order/checkout")
    public String createOrderLine(RedirectAttributes redirectAttributes, Principal principal) {
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isPresent()) {
            Optional<Order> optionalOrder = orderService.createAndReturnOrder(optionalUser.get());
            if (optionalOrder.isPresent()) {
                Order persistedOrder = orderService.saveOrder(optionalOrder.get());
                redirectAttributes.addFlashAttribute("order", persistedOrder);
                return "redirect:/order/success";
            }
        }
        //TODO show a error page
        return "Index";
    }

    @GetMapping("/order/success")
    public String orderSuccess(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("" +
                "isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "order/Success";
    }

}
