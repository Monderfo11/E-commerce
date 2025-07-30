package com.ecommerce_project.demo.Controller;


import com.ecommerce_project.demo.DTO.NewPriceDTO;
import com.ecommerce_project.demo.DTO.ProductDTO;
import com.ecommerce_project.demo.Entity.Product;
import com.ecommerce_project.demo.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-product")
    public ResponseEntity addCart (@RequestBody @Valid ProductDTO data){

        var product = this.adminService.addProduct(data);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);


    }

    @PutMapping("/update-price/{id}")
    public ResponseEntity updatePrice(@PathVariable Long id, @RequestBody @Valid NewPriceDTO data) {

        var product = this.adminService.updatePrice(id, data);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);

    }
}
