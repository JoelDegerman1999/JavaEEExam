package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;

    private int orderGrandTotal;

    private boolean orderSent;

    @ManyToOne
    private User user;

    public Order() {
        orderItems = new ArrayList<>();
    }
}
