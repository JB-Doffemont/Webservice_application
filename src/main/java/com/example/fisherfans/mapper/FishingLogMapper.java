package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.FishingLogDto;
import com.example.fisherfans.entity.FishingLog;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishingLogMapper {

    FishingLogMapper INSTANCE = Mappers.getMapper(FishingLogMapper.class);

    FishingLogDto entityToDto(FishingLog fishingLog);

    FishingLog dtoToEntity(FishingLogDto fishingLogdto);

    List<FishingLogDto> entitiesToDtos(List<FishingLog> fishingLogs);

    List<FishingLog> dtosToEntities(List<FishingLogDto> fishingLogDtos);

}