package com.example.fisherfans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.jwt.JwtRequest;
import com.example.fisherfans.jwt.JwtResponse;
import com.example.fisherfans.jwt.JwtTokenManager;
import com.example.fisherfans.service.Impl.JwtUserDetailsServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenManager tokenManager;
    @Autowired
    private HttpServletResponse response;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken);
        Cookie cookie = new Cookie("jwtToken", jwtToken);
        response.addCookie(cookie);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + jwtToken);
        User user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
        JwtResponse jwtResponse = new JwtResponse(authenticationRequest.getUsername(), user.getId(), jwtToken);
        return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    class UserDisabledException extends RuntimeException {
        public UserDisabledException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}