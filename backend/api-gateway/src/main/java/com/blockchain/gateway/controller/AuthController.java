package com.blockchain.gateway.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    
    // In-memory user store for demo (in production, use database)
    private final Map<String, DemoUser> users = new ConcurrentHashMap<>();
    private final Map<String, String> tokens = new ConcurrentHashMap<>();
    
    public AuthController() {
        // Pre-populate with demo user
        DemoUser demoUser = new DemoUser();
        demoUser.setId("demo-user-123");
        demoUser.setEmail("demo@example.com");
        demoUser.setPassword("password123");
        demoUser.setName("Demo User");
        users.put(demoUser.getEmail(), demoUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for: {}", request.getEmail());
        
        DemoUser user = users.get(request.getEmail());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
        
        String token = UUID.randomUUID().toString();
        tokens.put(token, user.getId());
        
        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build());
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        log.info("Signup attempt for: {}", request.getEmail());
        
        if (users.containsKey(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already registered"));
        }
        
        DemoUser newUser = new DemoUser();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setName(request.getName());
        users.put(request.getEmail(), newUser);
        
        String token = UUID.randomUUID().toString();
        tokens.put(token, newUser.getId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthResponse.builder()
                .token(token)
                .userId(newUser.getId())
                .email(newUser.getEmail())
                .name(newUser.getName())
                .build());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokens.remove(token);
        }
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not authenticated"));
        }
        
        String token = authHeader.substring(7);
        String userId = tokens.get(token);
        
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid token"));
        }
        
        DemoUser user = users.values().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }
        
        return ResponseEntity.ok(Map.of(
                "userId", user.getId(),
                "email", user.getEmail(),
                "name", user.getName()
        ));
    }
    
    // DTOs
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
    
    @Data
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
    }
    
    @Data
    @lombok.Builder
    public static class AuthResponse {
        private String token;
        private String userId;
        private String email;
        private String name;
    }
    
    @Data
    private static class DemoUser {
        private String id;
        private String email;
        private String password;
        private String name;
    }
}
