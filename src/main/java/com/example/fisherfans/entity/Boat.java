package com.example.fisherfans.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Data
@Entity
@Table(name = "boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boat_name", length = 255)
    @NotEmpty(message = "Le nom ne peut pas être vide")
    private String name;

    @Column(name = "boat_description", length = 255)
    private String description;

    @Column(name = "boat_brand", length = 255)
    private String brand;

    @Column(name = "boat_model", length = 4)
    private Integer manufacturingYear;

    @Column(name = "boat_photo_url", length = 255)
    private String photoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "boat_license_type", length = 20, nullable = false)
    @NotNull(message = "Le type de licence requis ne peut pas être vide")
    private LicenseType requiredLicenseType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NotNull(message = "Le type de bateau ne peut pas être vide")
    private BoatType boatType;

    @ElementCollection
    @CollectionTable(name = "boat_equipment")
    @Column(name = "equipment")
    @Enumerated(EnumType.STRING)
    private List<EquipmentType> equipment;

    @Column(name = "boat_desposit_amount")
    @DecimalMin(value = "0.0", message = "Le montant de la caution doit être positif")
    private Float depositAmount;

    @Column(name = "boat_max_passengers")
    @Min(value = 0, message = "La capacité maximale doit être positive")
    private Integer maxPassengers;

    @Column(name = "boat_number_of_berths")
    @Min(value = 0, message = "La capacité maximale doit être positive")
    private Integer numberOfBerths;

    @Column(name = "boat_home_port")
    @NotEmpty(message = "Le port d'attache ne peut pas être vide")
    private String homePort;

    @Column(name = "boat_port_latitude")
    @NotNull(message = "La latitude du port d'attache ne peut pas être nulle")
    private Double portLatitude;

    @Column(name = "boat_port_longitude")
    @NotNull(message = "La longitude du port d'attache ne peut pas être nulle")
    private Double portLongitude;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MotorType motorType;

    @Column(name = "engine_power")
    @Min(value = 0, message = "La puissance du moteur doit être positive")
    private Integer enginePower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    public enum LicenseType {
        COTIER, FLUVIAL
    }

    public enum BoatType {
        OPEN, CABINE, CATAMARAN, VOILIER, JETSKI, CANOE
    }

    public enum MotorType {
        DIESEL, ESSENCE, AUCUN
    }

    public enum EquipmentType {
        SONDEUR,
        VIVIER,
        ECHELLE,
        GPS,
        PORTE_CANNES,
        RADIO_VHF;
    }
}
