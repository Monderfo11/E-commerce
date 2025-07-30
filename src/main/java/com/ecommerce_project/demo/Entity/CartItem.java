package com.ecommerce_project.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "cart_items")
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    private Long quantity;
}
