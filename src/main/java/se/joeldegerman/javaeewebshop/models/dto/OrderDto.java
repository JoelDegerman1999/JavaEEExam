package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.entity.OrderItem;

import java.util.List;

@Data
public class OrderDto {

    private List<OrderItem> orderItems;

    private int orderGrandTotal;

    private boolean orderSent;

    private UserDto user;
}
