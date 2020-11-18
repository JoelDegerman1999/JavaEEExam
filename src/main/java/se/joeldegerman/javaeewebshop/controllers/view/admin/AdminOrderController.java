package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("order/all")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/order/Index";
    }

    @PostMapping("order/send/{id}")
    public String sendOrder(@PathVariable long id) {
        orderService.sendOrder(id);
        return "redirect:/admin/order/all";
    }

//    @GetMapping("get/order/newOrder")
//    public String getNonSentOrders(Model model) {
//        List<Order> nonSentOrders = orderService.getAllNonSentOrders();
//        model.addAttribute("nonSentOrders", nonSentOrders);
//        return "admin/order/Index";
//    }
//
//    @GetMapping("get/order/sentOrders")
//    public String sendOrders() {
//        List<Order> allSentOrders = orderService.getAllSentOrders();
//
//        return "redirect:/admin/";
//    }
}
