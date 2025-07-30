package com.ecommerce_project.demo.Repository;

import com.ecommerce_project.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findById(long id);
}
