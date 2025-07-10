package com.finance.expense_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.Dto.RegisterRequestDto;
import com.finance.expense_tracker.SecurityConfig.JwtUtil;
import com.finance.expense_tracker.dao.UserDao;
import com.finance.expense_tracker.exceptions.UserAlreadyExistsException;
import com.finance.expense_tracker.models.ApiResponse;
import com.finance.expense_tracker.models.LoginRequest;
import com.finance.expense_tracker.models.LoginResponse;
import com.finance.expense_tracker.models.User;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }

}
