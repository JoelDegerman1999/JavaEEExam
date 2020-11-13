package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.joeldegerman.javaeewebshop.models.entity.OrderLine;
import se.joeldegerman.javaeewebshop.repositories.OrderLineRepository;
import se.joeldegerman.javaeewebshop.services.OrderLineService;

@Controller
public class OrderController {

    private OrderLineService orderService;
    private OrderLineRepository orderLineRepository;

    public OrderController(OrderLineService orderService, OrderLineRepository orderLineRepository) {
        this.orderService = orderService;
        this.orderLineRepository = orderLineRepository;
    }

    @PostMapping("/order/checkout")
    public String createOrderLine(RedirectAttributes redirectAttributes) {
        OrderLine orderLine = orderService.createOrderLine();
        if(orderLine != null) {
            OrderLine persistedOrderLine = orderLineRepository.save(orderLine);
            redirectAttributes.addFlashAttribute("orderLine", persistedOrderLine);
            return "redirect:/order/success";
        }
        //TODO show a error page
        return "Index";
    }

    @GetMapping("/order/success")
    public String orderSuccess(@ModelAttribute("orderLine") OrderLine orderLine) {
        return "order/Success";
    }

}
