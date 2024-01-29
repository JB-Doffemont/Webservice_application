package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.service.Impl.BoatServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/boats")
public class BoatController {

    @Autowired
    BoatServiceImpl boatService;

    @GetMapping("")
    public List<BoatDto> getBoats() {
        return boatService.getBoats();
    }

    @GetMapping("/{id}")
    public BoatDto getBoat(@PathVariable("id") Long id) {
        return boatService.findBoatById(id);
    }

    @GetMapping("/paginated")
    public Page<BoatDto> getBoats(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        return this.boatService.getBoatsByPage(page, size);
    }

    @PostMapping("")
    public BoatDto createBoat(@RequestBody BoatDto boatDto) {
        return boatService.createBoat(boatDto);
    }

    @PutMapping("/{id}")
    public BoatDto updateBoat(@PathVariable("id") Long id, @RequestBody BoatDto boatDto) {
        return boatService.updateBoat(boatDto, id);
    }

    @PatchMapping("/{id}")
    public BoatDto patchBoat(@PathVariable("id") Long id, @RequestBody BoatDto boatDto) {
        return boatService.updateBoat(boatDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable("id") Long id) {
        boatService.deleteBoat(id);
    }

    @GetMapping("/criteria")
    public List<BoatDto> getBoatsByNumberOfBerths(@RequestParam Long numberOfBerths) {
        List<BoatDto> boats = boatService.getBoatsByNumberOfBerths(numberOfBerths);
        return boats;
    }

    @GetMapping("/search")
    public List<BoatDto> getBoatsByLocalisation(@RequestParam Double minLatitude, Double minLongitude,
            Double maxLatitude,
            Double maxLongitude) {
        List<BoatDto> boats = boatService.getBoatsByLocalisation(minLatitude, minLongitude, maxLatitude, maxLongitude);
        return boats;
    }
}
