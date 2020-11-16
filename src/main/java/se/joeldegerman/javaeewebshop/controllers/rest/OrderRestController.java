package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.OrderServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrderRestController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository, OrderServiceImpl orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

//    @PostMapping("/order/checkout")
//    public Long createOrderLine() {
//        Optional<Order> optionalOrder = orderService.createAndReturnOrder();
//        if (optionalOrder.isPresent()) {
//            Order persistedOrderLine = orderRepository.save(optionalOrder.get());
//            return persistedOrderLine.getId();
//        }
//        return 0L;
//    }

    @GetMapping("/orderline/{id}")
    public Order getOrderLine(@PathVariable long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) return optionalOrder.get();
        return null;
    }

    @GetMapping("/orderline")
    public List<Order> getAllOrderLines() {
        return orderRepository.findAll();
    }
}
