package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.FishingTripDto;
import com.example.fisherfans.entity.FishingTrip;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishingTripMapper {

    FishingTripMapper INSTANCE = Mappers.getMapper(FishingTripMapper.class);

    FishingTripDto entityToDto(FishingTrip fishingTrip);

    FishingTrip dtoToEntity(FishingTripDto fishingTripdto);

    List<FishingTripDto> entitiesToDtos(List<FishingTrip> fishingTrips);

    List<FishingTrip> dtosToEntities(List<FishingTripDto> fishingTripDtos);

}