package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.FishingTripDto;
import com.example.fisherfans.entity.FishingTrip;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishingTripMapper extends GenericMapper<FishingTrip, FishingTripDto> {

    FishingTripMapper INSTANCE = Mappers.getMapper(FishingTripMapper.class);

    @Override
    FishingTripDto entityToDto(FishingTrip fishingTrip);

    @Override
    FishingTrip dtoToEntity(FishingTripDto fishingTripdto);

    @Override
    List<FishingTripDto> entitiesToDtos(List<FishingTrip> fishingTrips);

    @Override
    List<FishingTrip> dtosToEntities(List<FishingTripDto> fishingTripDtos);

}