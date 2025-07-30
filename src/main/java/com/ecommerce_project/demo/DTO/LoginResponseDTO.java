package com.ecommerce_project.demo.DTO;

import com.ecommerce_project.demo.Entity.Role;

public record LoginResponseDTO (
    String token,

    Role role){
}
