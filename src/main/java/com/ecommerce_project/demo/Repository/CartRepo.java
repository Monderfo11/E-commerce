package com.ecommerce_project.demo.Repository;

import com.ecommerce_project.demo.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends JpaRepository<ShoppingCart, Long> {




}
