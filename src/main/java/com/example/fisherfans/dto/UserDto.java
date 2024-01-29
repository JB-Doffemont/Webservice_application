package com.example.fisherfans.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.fisherfans.entity.FishingLog;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstname;

    private boolean enabled;

    private String lastname;

    private String username;

    private Date birthDate;

    private String phone;

    private String address;

    private String postalCode;

    private String city;

    private List<String> languagesSpoken;

    private String avatarUrl;

    private String boatingLicenseNumber;

    private String insuranceNumber;

    private String status;

    private String companyName;

    private String activityType;

    private String siretNumber;

    private String commerceRegisterNumber;

    private List<String> roles;

    @JsonManagedReference
    private List<BoatDto> boats;

    private Collection<FishingTripDto> fishingTrips;

    private Collection<ReservationDto> reservations;

    private Collection<FishingLog> fishingLogs;

}