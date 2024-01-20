package com.example.fisherfans.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 180, unique = true, nullable = false)
    @Email(message = "Le format de l'adresse e-mail n'est pas valide")
    @NotEmpty(message = "L'adresse e-mail ne peut pas être vide")
    private String username;

    @Column(name = "lastname", length = 255, nullable = false)
    @NotEmpty(message = "Le nom ne peut pas être vide")
    private String lastname;

    @Column(name = "firstname", length = 255, nullable = false)
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    private String firstname;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La date de naissance ne peut pas être vide")
    private Date birthDate;

    @Column(name = "phone", length = 50, nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String phone;

    @Column(name = "address", length = 255, nullable = false)
    @NotEmpty(message = "L'adresse ne peut pas être vide")
    private String address;

    @Column(name = "postal_code", length = 20, nullable = false)
    @NotEmpty(message = "Le code postal ne peut pas être vide")
    private String postalCode;

    @Column(name = "city", length = 255, nullable = false)
    @NotEmpty(message = "La ville ne peut pas être vide")
    private String city;

    @ElementCollection
    @CollectionTable(name = "user_languages_spoken")
    @Column(name = "language")
    private List<String> languagesSpoken;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "boating_license_number", length = 8)
    @Pattern(regexp = "\\d{8}", message = "Le numéro de permis bateau doit contenir 8 chiffres")
    private String boatingLicenseNumber;

    @Column(name = "insurance_number", length = 12)
    @Pattern(regexp = "\\d{12}", message = "Le numéro d'assurance doit contenir 12 chiffres")
    private String insuranceNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    @NotNull(message = "Le statut ne peut pas être vide")
    private UserType status;

    @Column(name = "company_name", length = 255)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", length = 20, nullable = false)
    @NotNull(message = "Le type d'activité ne peut pas être vide")
    private ActivityType activityType;

    @Column(name = "siret_number", length = 255)
    @Pattern(regexp = "\\d{14}", message = "Le numéro SIRET doit contenir 14 chiffres")
    private String siretNumber;

    @Column(name = "RC_number", length = 255, nullable = false)
    @NotEmpty(message = "Le numéro de registre de commerce ne peut pas être vide")
    private String commerceRegisterNumber;

    @ElementCollection
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private List<String> roles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Password password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<Reservation> reservations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<FishingTrip> fishingTrips;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<Boat> boats;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<FishingLog> fishingLogs;

    // Statut (particulier | professionnel)
    public enum UserType {
        PARTICULAR, PROFESSIONNAL
    }

    // Type d’activité (location | guide de pêche)
    public enum ActivityType {
        LOCATION, GUIDED
    }
}
