package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrderRestController {

    final OrderService orderService;
    private OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @PostMapping("/order/checkout")
    public Long createOrderLine() {
        Optional<Order> optionalOrder = orderService.createOrder();
        if(optionalOrder.isPresent()) {
            Order persistedOrderLine = orderRepository.save(optionalOrder.get());
            return persistedOrderLine.getId();
        }
        return 0L;
    }

    @GetMapping("/orderline/{id}")
    public Order getOrderLine(@PathVariable long id) {
        return orderRepository.findById(id).get();
    }

    @GetMapping("/orderline")
    public List<Order> getAllOrderLines() {
        return orderRepository.findAll();
    }
}
