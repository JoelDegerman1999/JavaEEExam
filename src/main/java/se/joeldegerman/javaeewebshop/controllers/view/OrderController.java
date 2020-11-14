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
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.OrderService;

import java.util.Optional;

@Controller
public class OrderController {

    private OrderService orderService;
    private OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order/checkout")
    public String createOrderLine(RedirectAttributes redirectAttributes) {
        Optional<Order> optionalOrder = orderService.createOrder();
        if(optionalOrder.isPresent()) {
            Order persistedOrder = orderService.saveOrder(optionalOrder.get());
            redirectAttributes.addFlashAttribute("order", persistedOrder);
            return "redirect:/order/success";
        }
        //TODO show a error page
        return "Index";
    }

    @GetMapping("/order/success")
    public String orderSuccess(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "order/Success";
    }

}
