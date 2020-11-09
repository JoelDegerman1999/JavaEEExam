package se.joeldegerman.javaeewebshop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "OrderLines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Order> orders;

    private int grandTotal;

    public OrderLine() {
        orders = new ArrayList<>();
    }
}
