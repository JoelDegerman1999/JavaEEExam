package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.OrderLine;
import se.joeldegerman.javaeewebshop.repositories.OrderLineRepository;
import se.joeldegerman.javaeewebshop.services.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderRestController {

    final OrderLineService orderService;
    private OrderLineRepository orderLineRepository;

    public OrderRestController(OrderLineRepository orderLineRepository, OrderLineService orderService) {
        this.orderLineRepository = orderLineRepository;
        this.orderService = orderService;
    }

    @PostMapping("/order/checkout")
    public Long createOrderLine() {
        OrderLine orderLine = orderService.createOrderLine();
        if(orderLine != null) {
            OrderLine persistedOrderLine = orderLineRepository.save(orderLine);
            return persistedOrderLine.getId();
        }
        return 0L;
    }

    @GetMapping("/orderline/{id}")
    public OrderLine getOrderLine(@PathVariable long id) {
        return orderLineRepository.findById(id).get();
    }

    @GetMapping("/orderline")
    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }
}
