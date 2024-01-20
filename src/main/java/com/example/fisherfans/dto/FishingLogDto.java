package com.example.fisherfans.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FishingLogDto {

    private Long id;

    private String fishName;

    private String photoUrl;

    private String comment;

    private Float sizeCm;

    private Float weightKg;

    private String fishingLocation;

    private LocalDateTime fishingDate;

    private String fishReleased;

    private UserDto owner;

}
