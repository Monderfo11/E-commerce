package com.ecommerce_project.demo.Logic;

import com.ecommerce_project.demo.Entity.ShoppingCart;
import com.ecommerce_project.demo.Repository.CartItemRepo;
import com.ecommerce_project.demo.Repository.CartRepo;
import com.ecommerce_project.demo.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Logics {


    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    public Long calculateTotalPrice(Long cartID) {

        var cart = cartRepo.findById(cartID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        var cartItems = cartItemRepo.findAllByShoppingCart(cart);
        long totalPrice = 0;
        for(var item : cartItems) {
            var product = productRepo.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            totalPrice += product.getPrice() * item.getQuantity();

        }

        return totalPrice;

    }






}
