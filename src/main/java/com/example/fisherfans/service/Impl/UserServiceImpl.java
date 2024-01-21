package com.example.fisherfans.service.Impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.ResetPasswordDto;
import com.example.fisherfans.dto.UserDto;
import com.example.fisherfans.entity.ConfirmationToken;
import com.example.fisherfans.entity.Password;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.mail.GmailAPIService;
import com.example.fisherfans.mapper.UserMapper;
import com.example.fisherfans.repository.PasswordRepository;
import com.example.fisherfans.repository.UserRepository;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;

    @Autowired
    private GmailAPIService gmailAPIService;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        Password pwd = new Password();
        // Set the user for the password
        pwd.setUser(user);
        // Set the password for the user
        pwd.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword().getEncryptedPassword()));
        // Set the password for the user
        user.setPassword(pwd);
        // Save the user
        return userRepository.save(user);
    }

    public String registerUser(User user) {

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }

    public User updateUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.dtoToEntity(userDto);
        return userRepository.save(user);
    }

    public void checkUsernameUser(String username) throws MessagingException, IOException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User not found");
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        gmailAPIService.sendMessage("Réinitialiser Mot De Passe ",
                "http://localhost:8080/reset-password/" + confirmationToken.getConfirmationToken(), user.getUsername());
    }

    public void resetPasswordUser(ResetPasswordDto resetPassword) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenService.getToken(resetPassword.getToken());
        if (confirmationToken.isPresent()) {
            if (confirmationToken.get().getConfirmedAt() != null)
                throw new RuntimeException("Email already confirmed");
            if (confirmationToken.get().getExpiresAt().isBefore(LocalDateTime.now()))
                throw new RuntimeException("Token expired");
            User user = confirmationToken.get().getUser();
            Password pwd = passwordRepository.findByPassword(user.getPassword().getEncryptedPassword());
            pwd.setEncryptedPassword(bCryptPasswordEncoder.encode(resetPassword.getNewPassword()));
            user.setPassword(pwd);
            pwd.setUser(user);
            userRepository.save(user);
            confirmationTokenService.delete(confirmationToken);
        } else
            throw new RuntimeException("Token not found");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

}