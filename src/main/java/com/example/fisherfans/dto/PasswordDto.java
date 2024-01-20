package com.example.fisherfans.dto;

import lombok.Data;

@Data
public class PasswordDto {
	private Long id;
    private String oldPassword;
    private String newPassword;
    private String encryptedPassword;
	private UserDto user;
}