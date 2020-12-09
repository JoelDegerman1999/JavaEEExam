package se.joeldegerman.javaeewebshop.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItems;

    private int orderGrandTotal;

    private boolean orderSent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    public Order() {
        orderItems = new ArrayList<>();
    }
}
