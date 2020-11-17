package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("get/order/nonsent")
    public String getNonSentOrders(Model model) {

        model.addAttribute("nonSentOrders", nonSentOrders.get());
        return "admin/Test";
    }

    @GetMapping("get/order/send")
    public String sendOrders() {
        Optional<List<Order>> nonSentOrders = orderRepository.getNonSentOrders();
        if (nonSentOrders.isPresent()) {
            for (var order: nonSentOrders.get()) {
                order.setOrderSent(true);
                orderRepository.save(order);
            }
        }
        return "redirect:/admin/get/order/nonsent";
    }
}
