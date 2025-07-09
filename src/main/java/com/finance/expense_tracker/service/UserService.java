package com.finance.expense_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.finance.expense_tracker.Dto.RegisterRequestDto;
import com.finance.expense_tracker.dao.UserDao;
import com.finance.expense_tracker.exceptions.UserAlreadyExistsException;
import com.finance.expense_tracker.models.ApiResponse;
import com.finance.expense_tracker.models.User;

public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterRequestDto request) {
        if (userDao.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email " + request.getEmail() + " is already in use.");
        }

        if (userDao.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username " + request.getUsername() + " is already in use.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userDao.save(user);

        return new ApiResponse(true, "User registered successfully");
    }

}
