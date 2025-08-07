package com.springBoot.MyrPg.service;



import com.springBoot.MyrPg.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User register(User user); // Register a new user (used in AuthController)

    Optional<User> findByEmail(String email); // Used for login/authentication

    List<User> getAllUsers();  // Admin: fetch all registered users
}
