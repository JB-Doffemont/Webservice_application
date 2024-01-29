package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.ReservationDto;
import com.example.fisherfans.entity.Reservation;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationDto entityToDto(Reservation reservation);

    Reservation dtoToEntity(ReservationDto reservationdto);

    List<ReservationDto> entitiesToDtos(List<Reservation> reservations);

    List<Reservation> dtosToEntities(List<ReservationDto> reservationDtos);

}