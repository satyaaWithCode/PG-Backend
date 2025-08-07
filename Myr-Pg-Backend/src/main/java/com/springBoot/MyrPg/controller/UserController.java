package com.springBoot.MyrPg.controller;

import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import com.springBoot.MyrPg.entity.User;
import com.springBoot.MyrPg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security")
public class UserController {

//only Pg owner can able to access user details
    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponseDTO<List<User>> response = new ApiResponseDTO<>(
                "success",
                "All users fetched successfully",
                users
        );
        return ResponseEntity.ok(response);
    }

}
