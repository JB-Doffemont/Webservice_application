package com.example.fisherfans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.mapper.BoatMapper;
import com.example.fisherfans.repository.BoatRepository;
import com.example.fisherfans.repository.UserRepository;

@Service
public class BoatService {

    @Autowired
    BoatRepository boatRepository;

    BoatMapper boatMapper;

    @Autowired
    UserRepository userRepository;

    public List<Boat> getBoats() {
        // List<Boat> boats = boatRepository.findAll();
        // List<BoatDto> boatsDto = BoatMapper.INSTANCE.entitiesToDtos(boats);

        return boatRepository.findAll();
    }

    public Boat findBoatById(Long id) {
        return boatRepository.findById(id).orElse(null);
    }

    public Boat createBoat(BoatDto boatDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName);

        if (user.getBoatingLicenseNumber() != null) {

            Boat boat = BoatMapper.INSTANCE.dtoToEntity(boatDTO);
            return boatRepository.save(boat);
        } else {
            return null;
        }

    }

    public Boat updateBoat(BoatDto boatDto, Long id) {
        Boat boat = boatRepository.findById(id).orElse(null);
        BoatMapper.INSTANCE.updateEntityFromBoatDto(boatDto, boat);
        return boatRepository.save(boat);
    }

    public void deleteBoat(Long id) {
        boatRepository.deleteById(id);
    }

    public List<Boat> getBoatsByNumberOfBerths(Long numberOfBerths) {
        return boatRepository.findBoatsByNumberOfBerths(numberOfBerths);
    }

    public List<Boat> getBoatsByLocalisation(Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude) {
        return boatRepository.findBoatsByLocalisation(minLatitude, minLongitude, maxLatitude, maxLongitude);
    }
}