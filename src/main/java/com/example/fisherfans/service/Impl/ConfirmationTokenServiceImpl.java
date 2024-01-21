package com.example.fisherfans.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fisherfans.entity.ConfirmationToken;
import com.example.fisherfans.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenServiceImpl {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(LocalDateTime.now(), token);
    }

    public void delete(Optional<ConfirmationToken> confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken.get());
    }
}
