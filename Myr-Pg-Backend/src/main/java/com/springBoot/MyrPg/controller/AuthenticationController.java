package com.springBoot.MyrPg.controller;

import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import com.springBoot.MyrPg.DTO.AuthRequestDTO;
import com.springBoot.MyrPg.DTO.AuthResponseDTO;
import com.springBoot.MyrPg.entity.User;
import com.springBoot.MyrPg.security.JwtHelper;
import com.springBoot.MyrPg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "https://satyaa-pg.netlify.app")
public class AuthenticationController {

    private final UserService service;
    private final JwtHelper helper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<User>> register(@RequestBody User user) {
        // Hash plain-text password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = service.register(user);

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        "success",
                        "User registered successfully",
                        savedUser
                )
        );
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO request) {

        Optional<User> userOpt = service.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = helper.generateToken(
                        new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                List.of(() -> user.getRole())
                        )
                );

                AuthResponseDTO authResponse = new AuthResponseDTO(
                        token,
                        user.getRole(),
                        "Login successful"
                );

                ApiResponseDTO<AuthResponseDTO> response = new ApiResponseDTO<>(
                        "success",
                        "Login successful",
                        authResponse
                );

                return ResponseEntity.ok(response);
            }
        }

        ApiResponseDTO<AuthResponseDTO> errorResponse = new ApiResponseDTO<>(
                "error",
                "Invalid email or password",
                null
        );

        return ResponseEntity.status(401).body(errorResponse);
    }

}
