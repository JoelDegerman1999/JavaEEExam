package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.OrderLine;
import se.joeldegerman.javaeewebshop.repositories.OrderLineRepository;

import java.util.List;

@RestController
public class OrderRestController {

    private OrderLineRepository orderLineRepository;

    public OrderRestController(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    @GetMapping("/api/orderlines")
    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }
}
