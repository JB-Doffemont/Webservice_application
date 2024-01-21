package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;
import com.example.fisherfans.service.Impl.BoatServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/boats")
public class BoatController {

    @Autowired
    BoatServiceImpl boatService;

    @GetMapping("")
    public List<Boat> getBoats() {
        return boatService.getBoats();
    }

    @GetMapping("/{id}")
    public Boat getBoat(@PathVariable("id") Long id) {
        return boatService.findBoatById(id);
    }

    @GetMapping("/paginated")
    public Page<Boat> getBoats(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        return this.boatService.getBoatsByPage(page, size);
    }

    @PostMapping("")
    public Boat createBoat(@RequestBody BoatDto boatDto) {
        return boatService.createBoat(boatDto);
    }

    @PutMapping("/{id}")
    public Boat updateBoat(@PathVariable("id") Long id, @RequestBody BoatDto boatDto) {
        return boatService.updateBoat(boatDto, id);
    }

    @PatchMapping("/{id}")
    public Boat patchBoat(@PathVariable("id") Long id, @RequestBody BoatDto boatDto) {
        return boatService.updateBoat(boatDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable("id") Long id) {
        boatService.deleteBoat(id);
    }

    @GetMapping("/criteria")
    public List<Boat> getBoatsByNumberOfBerths(@RequestParam Long numberOfBerths) {
        List<Boat> boats = boatService.getBoatsByNumberOfBerths(numberOfBerths);
        return boats;
    }

    @GetMapping("/search")
    public List<Boat> getBoatsByLocalisation(@RequestParam Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude) {
        List<Boat> boats = boatService.getBoatsByLocalisation(minLatitude, minLongitude, maxLatitude, maxLongitude);
        return boats;
    }
}
