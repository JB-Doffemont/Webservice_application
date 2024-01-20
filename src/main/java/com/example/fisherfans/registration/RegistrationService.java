package com.example.fisherfans.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fisherfans.entity.ConfirmationToken;
import com.example.fisherfans.entity.Password;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.repository.UserRepository;
import com.example.fisherfans.service.ConfirmationTokenService;
import com.example.fisherfans.service.UserService;

import java.util.regex.Pattern;
import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserService userService;

    // Utilisation de constantes pour les messages d'erreur
    private static final String EMAIL_ALREADY_USED = "Email déjà utilisé";
    private static final String INVALID_EMAIL = "Email invalide";
    private static final String PASSWORD_VALIDATION_ERROR = "Le mot de passe doit contenir au moins un chiffre, une lettre minuscule, une lettre majuscule, un caractère spécial, pas d'espace blanc, au moins 8 caractères";

    @Transactional
    public String register(User request) throws RegistrationException {
        String username = request.getUsername();
        String emailRegex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}";
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        // Le mot de passe doit contenir au moins un chiffre, une lettre minuscule, une
        // lettre majuscule, un caractère spécial, pas d'espace blanc, au moins 8
        // caractères

        String password = request.getPassword().getEncryptedPassword();

        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new IllegalStateException(EMAIL_ALREADY_USED);
        }
        if (!Pattern.matches(emailRegex, request.getUsername())) {
            throw new IllegalStateException(INVALID_EMAIL);
        }

        if (password.matches(passwordRegex)) {
            User newUser = User.builder()
                    .username(username)
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .birthDate(request.getBirthDate())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .postalCode(request.getPostalCode())
                    .city(request.getCity())
                    .languagesSpoken(request.getLanguagesSpoken())
                    .avatarUrl(request.getAvatarUrl())
                    .boatingLicenseNumber(request.getBoatingLicenseNumber())
                    .insuranceNumber(request.getInsuranceNumber())
                    .status(request.getStatus())
                    .companyName(request.getCompanyName())
                    .activityType(request.getActivityType())
                    .siretNumber(request.getSiretNumber())
                    .roles(request.getRoles())
                    .commerceRegisterNumber(request.getCommerceRegisterNumber())
                    .password(new Password(password))
                    .build();

            // Transactional
            User generatedUser = userService.createUser(newUser);
            String token = userService.registerUser(generatedUser);
            logger.info("token generated: {}", token);
            return token;
        } else {
            throw new IllegalStateException(PASSWORD_VALIDATION_ERROR);
        }
    }

    public boolean confirmToken(String token) throws IllegalStateException {
        try {
            ConfirmationToken confirmationToken = confirmationTokenService
                    .getToken(token)
                    .orElseThrow(() -> new IllegalStateException("Token not found for: " + token));
    
            if (confirmationToken.getConfirmedAt() != null) {
                throw new IllegalStateException("Email already confirmed");
            }
    
            LocalDateTime expiredAt = confirmationToken.getExpiresAt();
    
            if (expiredAt.isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("Token expired");
            }
    
            User user = confirmationToken.getUser();
            user.setEnabled(true);
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            userRepository.save(user);  
            return user.isEnabled();
        } catch (Exception e) {
            e.printStackTrace(); 
            throw e;
        }
    }
    
}
