package com.example.fisherfans.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.mapper.BoatMapper;
import com.example.fisherfans.repository.BoatRepository;
import com.example.fisherfans.repository.UserRepository;
import com.example.fisherfans.service.BoatService;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    BoatRepository boatRepository;

    @Autowired
    BoatMapper boatMapper;

    @Autowired
    UserRepository userRepository;

    public List<BoatDto> getBoats() {
        return boatMapper.entitiesToDtos(boatRepository.findAll());
    }

    public BoatDto findBoatById(Long id) {
        BoatDto boatDto = boatMapper.entityToDto(boatRepository.findById(id).orElse(null));
        return boatDto;
    }

    public Page<BoatDto> getBoatsByPage(int pageNumber, int pageSize) {
        try {
            Page<Boat> boatPage = boatRepository.findAll(PageRequest.of(pageNumber, pageSize));
            List<BoatDto> boatDtos = boatMapper.entitiesToDtos(boatPage.getContent());

            return new PageImpl<>(boatDtos, PageRequest.of(pageNumber, pageSize), boatPage.getTotalElements());
        } catch (Exception e) {
            throw new BoatServiceException("Error retrieving boats by page", e);
        }
    }

    public BoatDto createBoat(BoatDto boatDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName);

        if (user.getBoatingLicenseNumber() != null) {

            Boat boat = boatMapper.dtoToEntity(boatDTO);
            boat.setOwner(user);
            return boatMapper.entityToDto(boatRepository.save(boat));
        } else {
            return null;
        }
    }

    public BoatDto updateBoat(BoatDto boatDto, Long id) {
        Boat boat = boatRepository.findById(id).orElse(null);
        boatMapper.updateEntityFromBoatDto(boatDto, boat);
        return boatMapper.entityToDto(boatRepository.save(boat));
    }

    public void deleteBoat(Long id) {
        boatRepository.deleteById(id);
    }

    public List<BoatDto> getBoatsByNumberOfBerths(Long numberOfBerths) {
        return boatMapper.entitiesToDtos(boatRepository.findBoatsByNumberOfBerths(numberOfBerths));
    }

    public List<BoatDto> getBoatsByLocalisation(Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude) {
        return boatMapper.entitiesToDtos(
                boatRepository.findBoatsByLocalisation(minLatitude, minLongitude, maxLatitude, maxLongitude));
    }

    // Custom Exception for Boat Service Errors
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public class BoatServiceException extends RuntimeException {
        public BoatServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}