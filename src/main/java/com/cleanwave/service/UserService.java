package com.cleanwave.service;

import com.cleanwave.dto.AuthResponse;
import com.cleanwave.dto.LoginRequest;
import com.cleanwave.dto.SignupRequest;
import com.cleanwave.exception.BadRequestException;
import com.cleanwave.exception.UnauthorizedException;
import com.cleanwave.model.User;
import com.cleanwave.repository.UserRepository;
import com.cleanwave.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Set role
        if (request.getRole() != null) {
            try {
                user.setRole(User.UserRole.valueOf(request.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                user.setRole(User.UserRole.CITIZEN);
            }
        } else {
            user.setRole(User.UserRole.CITIZEN);
        }
        
        if (user.getRole() == null) {
            throw new BadRequestException("Unable to set user role");
        }
        
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        org.springframework.security.core.authority.AuthorityUtils
                                .createAuthorityList("ROLE_" + user.getRole().name())
                )
        );
        
        return new AuthResponse(token, user.getEmail(), user.getUsername(), user.getRole().name());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        org.springframework.security.core.authority.AuthorityUtils
                                .createAuthorityList("ROLE_" + user.getRole().name())
                )
        );
        
        return new AuthResponse(token, user.getEmail(), user.getUsername(), user.getRole().name());
    }
    
    public List<User> getAllWorkers() {
        return userRepository.findByRole(User.UserRole.WORKER);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));
    }
    
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UnauthorizedException("User not found"));
    }
}
