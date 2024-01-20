package com.example.fisherfans.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.fisherfans.entity.User;

import lombok.Data;

@Data
public class ConfirmationTokenDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private transient User user;
}
