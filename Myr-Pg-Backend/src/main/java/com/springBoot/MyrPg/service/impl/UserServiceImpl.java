package com.springBoot.MyrPg.service.impl;

import com.springBoot.MyrPg.entity.User;
import com.springBoot.MyrPg.exception.DuplicateUserException;
import com.springBoot.MyrPg.repository.UserRepository;
import com.springBoot.MyrPg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User register(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateUserException("User already exists with email: " + user.getEmail());
        }
        return repository.save(user); //user register here
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email); //admin and user able to login
    }

    @Override
    public List<User> getAllUsers() { //admin
        return  repository.findAll();
    }
}
