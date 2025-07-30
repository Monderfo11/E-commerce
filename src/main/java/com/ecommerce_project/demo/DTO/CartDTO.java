package com.ecommerce_project.demo.DTO;

public record CartDTO(
        Long userId,
        Long productId,
        Long quantity){
}
