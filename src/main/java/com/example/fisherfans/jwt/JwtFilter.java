package com.example.fisherfans.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fisherfans.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
// JwtFilter allows us to intercept the request and validate the token
// before the request is passed to the controller
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenManager tokenManager;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = getTokenHeader(request, response);
        String token = getTokenFromHeader(tokenHeader);
        String username = getUsernameFromToken(token);
        UserDetails userDetails = getUserDetails(username);
        boolean isTokenValid = isTokenValid(token, userDetails);

        if (isAuthenticationRequired(username, isTokenValid)) {
            setAuthentication(request, userDetails);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenHeader(HttpServletRequest request, HttpServletResponse response) {
        String BEARER_PREFIX = "Bearer ";
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith(BEARER_PREFIX)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwtToken")) {
                        String token = cookie.getValue();
                        response.setHeader("Authorization", BEARER_PREFIX + token);
                        return BEARER_PREFIX + token;
                    }
                }
            }
        }
        return tokenHeader;
    }

    private String getTokenFromHeader(String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith("Bearer")) {
            return tokenHeader.substring("Bearer".length()).trim();
        }
        return null;
    }

    private String getUsernameFromToken(String token) {
        if (token != null) {
            try {
                return tokenManager.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
        }
        return null;
    }

    private UserDetails getUserDetails(String username) {
        if (username != null) {
            return userDetailsService.loadUserByUsername(username);
        }
        return null;
    }

    private boolean isTokenValid(String token, UserDetails userDetails) {
        if (token != null && userDetails != null) {
            return tokenManager.validateJwtToken(token, userDetails);
        }
        return false;
    }

    private boolean isAuthenticationRequired(String username, boolean isTokenValid) {
        return username != null && SecurityContextHolder.getContext().getAuthentication() == null && isTokenValid;
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
