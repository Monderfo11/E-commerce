package com.ecommerce_project.demo.Controller;


import com.ecommerce_project.demo.DTO.CartDTO;
import com.ecommerce_project.demo.Logic.Logics;
import com.ecommerce_project.demo.Repository.CartRepo;
import com.ecommerce_project.demo.Repository.UserRepo;
import com.ecommerce_project.demo.Service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    Logics logics;


    @PostMapping("/add-product")
    public ResponseEntity addCart(@RequestBody @Valid CartDTO data) {

        var cart = this.cartService.addProduct(data);
        if (cart == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/remove-product/{idP}/{idC}")
    public  ResponseEntity<Void> removeProduct(@PathVariable Long idI, @PathVariable Long idC) {

        return cartService.removeProduct(idI, idC) ? ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();


    }

    @GetMapping("/get-total-cart/{id}")
    public ResponseEntity<Long> getTotalCart(@PathVariable Long id) {

        var total = logics.calculateTotalPrice(id);
        if (total == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(total);
    }
}
