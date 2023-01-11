package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderDetails {
    @OneToMany
    List<Product> products = new ArrayList<Product>();
    @OneToMany
    List<OrderDetails> orders = new ArrayList<OrderDetails>();
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public List<Product> getProducts() {
        return products;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public Integer getId() {
        return id;
    }
}
