package com.example.fisherfans.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishingTripDto {

    private Long id;

    private String title;

    private String informations;

    private String fishingTripType;

    private String rateType;

    private LocalDate startingDate;

    private LocalDate endingDate;

    private LocalTime startingTime;

    private LocalTime endingTime;

    private Integer passengerNumber;

    private Double price;

    private List<ReservationDto> reservations;

    private BoatDto boat;

    private UserDto owner;
}