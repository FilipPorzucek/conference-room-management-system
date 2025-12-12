package com.booking.controller;


import com.booking.dto.LoginRequests;
import com.booking.dto.RegisterRequest;
import com.booking.model.User;
import com.booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.booking.config.service.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if(userRepository.findByEmail(registerRequest.getEmail())!=null){
            return ResponseEntity.badRequest().body("Email is already exists");
        }

        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequests loginRequests){
        User user=userRepository.findByEmail(loginRequests.getEmail());

        if (user==null || !passwordEncoder.matches(loginRequests.getPassword(),user.getPassword())){
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        String token=jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }



}
