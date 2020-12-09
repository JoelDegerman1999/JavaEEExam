package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;
import se.joeldegerman.javaeewebshop.services.OrderServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;
import se.joeldegerman.javaeewebshop.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderServiceImpl orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/order/checkout")
    public String createOrderLine(RedirectAttributes redirectAttributes, Principal principal) {
        Optional<User> optionalUser = userService.getByName(principal.getName());
        if (optionalUser.isPresent()) {
            Optional<Order> optionalOrder = orderService.createAndReturnOrder(optionalUser.get());
            if (optionalOrder.isPresent()) {
                Order persistedOrder = orderService.save(optionalOrder.get());
                redirectAttributes.addFlashAttribute("order", persistedOrder);
                return "redirect:/order/success";
            }
        }
        return "error/500";
    }

    @GetMapping("/order/success")
    public String orderSuccess(@ModelAttribute("order") Order order) {
        return "Order/Success";
    }

    //    AJAX METHODS
    @GetMapping("ajax/order")
    @ResponseBody
    public List<Order> getMyOrders(@AuthenticationPrincipal CustomUserDetail userDetail) {
        return orderService.findOrderByUsername(userDetail.getUsername());
    }

}
