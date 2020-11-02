package se.joeldegerman.javaeewebshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.OrderLine;
import se.joeldegerman.javaeewebshop.repositories.OrderLineRepository;
import se.joeldegerman.javaeewebshop.services.CartService;
import se.joeldegerman.javaeewebshop.services.OrderService;

@Controller
public class OrderController {

    private OrderService orderService;
    private OrderLineRepository orderLineRepository;

    public OrderController(OrderService orderService, OrderLineRepository orderLineRepository) {
        this.orderService = orderService;
        this.orderLineRepository = orderLineRepository;
    }

    @PostMapping("/cart/checkout")
    public String createOrderLine() {
        OrderLine orderLine = orderService.createOrderLine();
        if(orderLine != null) {
        orderLineRepository.save(orderLine);
        }
        return "redirect:/";
    }
}
