package com.example.fisherfans.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fisherfans.entity.User;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RequestMapping("/auth")
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    private boolean mailTokenConfirm;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User request) {
        try {
            String token = registrationService.register(request);
    
            if (mailTokenConfirm && !token.isEmpty()) {
                registrationService.confirmToken(token);
            }
    
            return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
        } catch (RegistrationException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Registration failed"));
        }
    }
    

    @PostMapping("/register-token-confirmed")
    public ResponseEntity<Object> registerConfirm(@RequestBody User request) throws RegistrationException {
        try {
            if (!mailTokenConfirm) {
                String token = registrationService.register(request);
                if (!token.isEmpty()) {
                    registrationService.confirmToken(token);
                }
                return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
            } else {
                return ResponseEntity.ok(Collections.singletonMap("message", registrationService.register(request)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    

    @PostMapping("/confirm-token")
    public ResponseEntity<?> confirm(@RequestParam String token) {
        try {
            boolean res = registrationService.confirmToken(token);
            if (res) {
                return ResponseEntity.ok().build();
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
