package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.FishingLogDto;
import com.example.fisherfans.entity.FishingLog;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishingLogMapper extends GenericMapper<FishingLog, FishingLogDto> {

    FishingLogMapper INSTANCE = Mappers.getMapper(FishingLogMapper.class);

    @Override
    FishingLogDto entityToDto(FishingLog fishingLog);

    @Override
    FishingLog dtoToEntity(FishingLogDto fishingLogdto);

    @Override
    List<FishingLogDto> entitiesToDtos(List<FishingLog> fishingLogs);

    @Override
    List<FishingLog> dtosToEntities(List<FishingLogDto> fishingLogDtos);

}