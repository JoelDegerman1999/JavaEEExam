package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.services.OrderServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrderRestController {

    private final OrderService orderService;
    public OrderRestController( OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderline/{id}")
    public Order getOrderLine(@PathVariable long id) {
        Optional<Order> optionalOrder = orderService.getById(id);
        if(optionalOrder.isPresent()) return optionalOrder.get();
        return null;
    }

    @GetMapping("/orderline")
    public List<Order> getAllOrderLines() {
        return orderService.getAll();
    }
}
