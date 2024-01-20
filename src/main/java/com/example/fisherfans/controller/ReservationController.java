package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.ReservationDto;
import com.example.fisherfans.entity.Reservation;
import com.example.fisherfans.service.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("")
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") Long id) {
        return reservationService.findReservationById(id);
    }

    @PostMapping("")
    public Reservation createReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.updateReservation(reservationDto);
    }

    @PatchMapping("/{id}")
    public Reservation patchReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.updateReservation(reservationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
    }

}
