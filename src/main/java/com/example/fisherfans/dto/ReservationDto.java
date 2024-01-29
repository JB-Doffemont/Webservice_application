package com.example.fisherfans.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {

    private Long id;

    private LocalDate reservationDate;

    private Integer seatNumber;

    private Double totalPrice;

    private FishingTripDto fishingTrip;

    private UserDto ownerDto;

}