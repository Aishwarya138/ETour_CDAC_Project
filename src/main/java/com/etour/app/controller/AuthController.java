package com.etour.app.controller;

import com.etour.app.config.CustomUserDetails;
import com.etour.app.dto.AuthRequest;
import com.etour.app.dto.AuthResponse;
import com.etour.app.entity.CustomerMaster;
import com.etour.app.repository.CustomerRepository;
import com.etour.app.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(
                    jwt,
                    userDetails.getCustomer().getId(),
                    userDetails.getCustomer().getName(),
                    userDetails.getCustomer().getEmail()));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerMaster customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        CustomerMaster savedCustomer = customerRepository.save(customer);

        // Auto-login after register (optional, but returning token helps)
        String jwt = jwtUtils.generateToken(savedCustomer.getEmail());

        return ResponseEntity.ok(new AuthResponse(
                jwt,
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail()));
    }
}
