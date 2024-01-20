package com.example.fisherfans.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "fishing_log")
public class FishingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "fish_name", length = 255)
    private String fishName;

    @Column(name = "photo_url", length = 255)
    private String photoUrl;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "size_cm")
    private Float sizeCm;

    @Column(name = "weight_kg")
    private Float weightKg;

    @Column(name = "fishing_location", length = 255)
    private String fishingLocation;

    @NotNull
    @Column(name = "fishing_date")
    private LocalDateTime fishingDate;

    @NotNull
    @Column(name = "fish_released")
    private Boolean fishReleased;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}