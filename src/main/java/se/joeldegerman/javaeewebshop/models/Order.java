package se.joeldegerman.javaeewebshop.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToOne
    private Product product;
    private int quantity;

    public Order() {
    }
}
