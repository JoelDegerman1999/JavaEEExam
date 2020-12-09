package se.joeldegerman.javaeewebshop.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.dto.OrderDto;
import se.joeldegerman.javaeewebshop.models.dto.UserDto;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;
import se.joeldegerman.javaeewebshop.services.interfaces.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrderRestController {
    private final OrderService orderService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public OrderRestController(OrderService orderService, UserService userService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("myOrders")
    public List<OrderDto> getOrderFromUser(Principal principal) {
        System.out.println(principal.getName());
        List<Order> orders = orderService.findOrderByUsername(principal.getName());
        return convertToListOfDto(orders);
    }

    @PostMapping("checkout")
    public Optional<Order> createOrder(Principal principal) {
        Optional<User> optionalUser = userService.getByName(principal.getName());
        if (optionalUser.isEmpty()) return Optional.empty();
        Optional<Order> optionalOrder = orderService.createAndReturnOrder(optionalUser.get());
        if (optionalOrder.isPresent()) {
            Order order = orderService.save(optionalOrder.get());
            return Optional.of(order);
        }
        return Optional.empty();
    }


    /*
    Mapper methods
     */
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
