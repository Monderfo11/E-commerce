package com.ecommerce_project.demo.DTO;

import com.ecommerce_project.demo.Entity.Role;

public record RegisterDTO(String username, String password, Role role) {
}
