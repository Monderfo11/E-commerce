package com.ecommerce_project.demo.Service;


import com.ecommerce_project.demo.DTO.CartDTO;
import com.ecommerce_project.demo.Entity.CartItem;
import com.ecommerce_project.demo.Entity.Product;
import com.ecommerce_project.demo.Entity.ShoppingCart;
import com.ecommerce_project.demo.Entity.User;
import com.ecommerce_project.demo.Logic.Logics;
import com.ecommerce_project.demo.Repository.CartItemRepo;
import com.ecommerce_project.demo.Repository.CartRepo;
import com.ecommerce_project.demo.Repository.ProductRepo;
import com.ecommerce_project.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    Logics logics;


    public ShoppingCart addProduct(CartDTO data) {

        User user = userRepo.findById(data.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingCart cart = user.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());



        }

        Product product = productRepo.findById(data.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(data.quantity());
        cartItem.setProduct(product);
        cartItem.setShoppingCart(cart);
        cart.getItems().add(cartItem);

        return cartRepo.save(cart);







    }

    public boolean removeProduct(Long idI, Long idC) {


        ShoppingCart cart = cartRepo.findById(idC)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            return false; // No products to remove
        } else {
            CartItem itemToRemove = cart.getItems()
                    .stream()
                    .filter(i -> i.getId().equals(idI))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found in cart"));

            cart.getItems().remove(itemToRemove);
            cartRepo.save(cart);
            return true;
        }


    }



}

