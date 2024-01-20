package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.ReservationDto;
import com.example.fisherfans.entity.Reservation;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper extends GenericMapper<Reservation, ReservationDto> {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Override
    ReservationDto entityToDto(Reservation reservation);

    @Override
    Reservation dtoToEntity(ReservationDto reservationdto);

    @Override
    List<ReservationDto> entitiesToDtos(List<Reservation> reservations);

    @Override
    List<Reservation> dtosToEntities(List<ReservationDto> reservationDtos);

}