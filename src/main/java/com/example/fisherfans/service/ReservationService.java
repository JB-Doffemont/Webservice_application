package com.example.fisherfans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.ReservationDto;
import com.example.fisherfans.entity.Reservation;
import com.example.fisherfans.mapper.ReservationMapper;
import com.example.fisherfans.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    ReservationMapper reservationMapper;

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.INSTANCE.dtoToEntity(reservationDto);
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.INSTANCE.dtoToEntity(reservationDto);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
