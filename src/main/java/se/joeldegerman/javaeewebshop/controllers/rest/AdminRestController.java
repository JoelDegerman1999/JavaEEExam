package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class AdminRestController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminRestController(ProductServiceImpl productService, UserRepository userRepository, OrderRepository orderRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("test")
    public List<Order> orders() {
        return orderRepository.getNonSentOrders().get();
    }

    @DeleteMapping("admin/delete/{id}")
    public void deleteProduct(@PathVariable(name = "id")long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("admin/get/order/notsent")
    public List<Order> getNonSentOrder() {
        Optional<List<Order>> nonSentOrders = orderRepository.getNonSentOrders();
        if(nonSentOrders.isPresent()) return nonSentOrders.get();
        return new ArrayList<>();
    }
}
