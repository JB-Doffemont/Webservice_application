package com.example.fisherfans.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    String token;
    String newPassword;
}