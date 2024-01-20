package com.example.fisherfans.dto;

import java.util.List;

import com.example.fisherfans.entity.Boat.BoatType;
import com.example.fisherfans.entity.Boat.EquipmentType;
import com.example.fisherfans.entity.Boat.LicenseType;
import com.example.fisherfans.entity.Boat.MotorType;

import lombok.Data;

@Data
public class BoatDto {

    private Long id;

    private String name;

    private String description;

    private String brand;

    private Integer manufacturingYear;

    private String photoURL;

    private LicenseType requiredLicenseType;

    private BoatType boatType;

    private List<EquipmentType> equipment;

    private Float depositAmount;

    private Integer maxPassengers;

    private Integer numberOfBerths;

    private String homePort;

    private Double portLatitude;

    private Double portLongitude;

    private MotorType motorType;

    private Integer enginePower;

    private UserDto owner;

}
