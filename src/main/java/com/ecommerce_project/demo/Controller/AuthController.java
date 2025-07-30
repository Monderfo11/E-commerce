package com.ecommerce_project.demo.Controller;


import com.ecommerce_project.demo.DTO.AuthDTO;
import com.ecommerce_project.demo.DTO.LoginResponseDTO;
import com.ecommerce_project.demo.DTO.RegisterDTO;
import com.ecommerce_project.demo.Entity.User;
import com.ecommerce_project.demo.Repository.UserRepo;
import com.ecommerce_project.demo.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
        var userpassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = this.authenticationManager.authenticate(userpassword);

        var token = this.tokenService.generateToken((User) authentication.getPrincipal());
        var role = ((User) authentication.getPrincipal()).getRole();

        return ResponseEntity.ok(new LoginResponseDTO(token, role));


    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {

        if (this.userRepo.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.username(),encryptedPassword, data.role());

        this.userRepo.save(user);

        return ResponseEntity.ok().build();
    }

}
