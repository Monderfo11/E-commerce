package com.ecommerce_project.demo.Repository;

import com.ecommerce_project.demo.Entity.CartItem;
import com.ecommerce_project.demo.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {


    @Query("SELECT c FROM CartItem c WHERE c.shoppingCart = ?1")
    List<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
}
