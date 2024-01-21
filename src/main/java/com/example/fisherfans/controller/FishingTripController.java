package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.FishingTripDto;
import com.example.fisherfans.entity.FishingTrip;
import com.example.fisherfans.service.Impl.FishingTripServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/fishing-trips")
public class FishingTripController {

    @Autowired
    FishingTripServiceImpl fishingTripService;

    @GetMapping("")
    public List<FishingTrip> getFishingTrips() {
        return fishingTripService.getFishingTrips();
    }

    @GetMapping("/{id}")
    public FishingTrip getFishingTrip(@PathVariable("id") Long id) {
        return fishingTripService.findFishingTripById(id);
    }

    @PostMapping("")
    public FishingTrip createFishingTrip(@RequestBody FishingTripDto fishingTripDto) {
        return fishingTripService.createFishingTrip(fishingTripDto);
    }

    @PutMapping("/{id}")
    public FishingTrip updateFishingTrip(@RequestBody FishingTripDto fishingTripDto) {
        return fishingTripService.updateFishingTrip(fishingTripDto);
    }

    @PatchMapping("/{id}")
    public FishingTrip patchFishingTrip(@RequestBody FishingTripDto fishingTripDto) {
        return fishingTripService.updateFishingTrip(fishingTripDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFishingTrip(@RequestParam Long id) {
        fishingTripService.deleteFishingTrip(id);
    }

}
