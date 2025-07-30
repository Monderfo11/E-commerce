package com.ecommerce_project.demo.Service;


import com.ecommerce_project.demo.DTO.NewPriceDTO;
import com.ecommerce_project.demo.DTO.ProductDTO;
import com.ecommerce_project.demo.Entity.Product;
import com.ecommerce_project.demo.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private ProductRepo productRepo;


    public Product addProduct(ProductDTO data) {

        Product newProduct = new Product();

        newProduct.setName(data.name());
        newProduct.setPrice(data.price());
        newProduct.setDescription(data.description());
        newProduct.setStock(data.stock());

        return productRepo.save(newProduct);
    }


    public Product updatePrice(Long id, NewPriceDTO data) {

        Product existingProduct = productRepo.findById(id).
                orElseThrow(()-> new RuntimeException("Product not found"));
        if (existingProduct == null) {
            return null;
        }

        var price = data.newPrice();

        if (price <= 0) {
            return null;
        }

        existingProduct.setPrice(price);

        return productRepo.save(existingProduct);





    }


}
