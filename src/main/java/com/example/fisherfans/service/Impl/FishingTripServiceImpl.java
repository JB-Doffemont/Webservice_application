package com.example.fisherfans.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.FishingTripDto;
import com.example.fisherfans.entity.FishingTrip;
import com.example.fisherfans.mapper.FishingTripMapper;
import com.example.fisherfans.repository.FishingTripRepository;

@Service
public class FishingTripServiceImpl {

    @Autowired
    FishingTripRepository fishingTripRepository;

    FishingTripMapper fishingTripMapper;

    public List<FishingTrip> getFishingTrips() {
        return fishingTripRepository.findAll();
    }

    public FishingTrip findFishingTripById(Long id) {
        return fishingTripRepository.findById(id).orElse(null);
    }

    public FishingTrip createFishingTrip(FishingTripDto fishingTripDTO) {
        FishingTrip fishingTrip = FishingTripMapper.INSTANCE.dtoToEntity(fishingTripDTO);
        return fishingTripRepository.save(fishingTrip);
    }

    public FishingTrip updateFishingTrip(FishingTripDto fishingTripDto) {
        FishingTrip fishingTrip = FishingTripMapper.INSTANCE.dtoToEntity(fishingTripDto);
        return fishingTripRepository.save(fishingTrip);
    }

    public void deleteFishingTrip(Long id) {
        fishingTripRepository.deleteById(id);
    }

}
