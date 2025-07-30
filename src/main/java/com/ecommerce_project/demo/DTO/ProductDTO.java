package com.ecommerce_project.demo.DTO;

public record ProductDTO(
    String name,
    String description,
    Double price,
    Long stock

) {
}
