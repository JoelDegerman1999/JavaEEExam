package se.joeldegerman.javaeewebshop.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToOne
    private Product product;
    private int quantity;
    private double orderItemTotal;

    public OrderItem() {
    }
}
