package com.example.fisherfans.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fisherfans.entity.User;
import com.example.fisherfans.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found with username: " + username + " or your account is not activated");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword().getEncryptedPassword(),
                new ArrayList<>());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
