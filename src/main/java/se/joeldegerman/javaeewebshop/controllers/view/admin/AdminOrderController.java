package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("all")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "Admin/Order/Index";
    }

    @GetMapping("{id}")
    public String getOrderById(@PathVariable long id, Model model) {
        Optional<Order> optionalOrder = orderService.getById(id);
        optionalOrder.ifPresent(order -> model.addAttribute("order", order));
        return "Admin/Order/Details";
    }

    @PostMapping("send/{id}")
    public RedirectView sendOrder(@PathVariable long id) {
        orderService.send(id);
        return new RedirectView("/admin/order/all");
    }

}
