package se.joeldegerman.javaeewebshop.controllers.rest.admin;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.dto.OrderDto;
import se.joeldegerman.javaeewebshop.models.dto.UserDto;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/order/")
public class AdminOrderRestController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public AdminOrderRestController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("all")
    public List<OrderDto> getAll() {
        return convertToListOfDto(orderService.getAll());
    }

    @GetMapping("{id}")
    public Optional<OrderDto> getById(@PathVariable long id) {
        Optional<Order> optionalOrder = orderService.getById(id);
        if (optionalOrder.isEmpty()) return Optional.empty();
        return Optional.of(convertToDto(optionalOrder.get()));
    }

    @PostMapping("markSent/{id}")
    public Optional<OrderDto> markOrderSent(@PathVariable long id) {
        Optional<Order> optionalOrder = orderService.send(id);
        if (optionalOrder.isEmpty()) return Optional.empty();
        return Optional.of(convertToDto(optionalOrder.get()));
    }


    /*
    Mapper methods
     */

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setUser(modelMapper.map(order.getUser(), UserDto.class));
        return orderDto;
    }

    private List<OrderDto> convertToListOfDto(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            var userDto = modelMapper.map(order.getUser(), UserDto.class);
            var orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setUser(userDto);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }
}
