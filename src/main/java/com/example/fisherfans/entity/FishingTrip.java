package com.example.fisherfans.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fishing_trip")
public class FishingTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "informations", columnDefinition = "TEXT")
    private String informations;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private FishingTripType fishingTripType;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate", length = 20)
    private RateType rateType;

    @NotNull
    @Column(name = "starting_date")
    private LocalDate startingDate;

    @jakarta.validation.constraints.NotNull
    @Column(name = "ending_date")
    private LocalDate endingDate;

    @NotNull
    @Column(name = "starting_time")
    private LocalTime startingTime;

    @NotNull
    @Column(name = "ending_time")
    private LocalTime endingTime;

    @Min(value = 1)
    @Column(name = "passenger_number")
    private Integer passengerNumber;

    @Min(value = 0)
    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "fishingTrip", targetEntity = Reservation.class, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Type de sortie (journalière | récurrente)
    public enum FishingTripType {
        DAILY, RECURRING;
    }

    // Type de tarification (globale | par personne)
    public enum RateType {
        GLOBAL, PER_PERSON;
    }
}
