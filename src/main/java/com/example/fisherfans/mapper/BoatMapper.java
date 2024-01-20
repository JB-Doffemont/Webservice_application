package com.example.fisherfans.mapper;

import java.util.List;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoatMapper extends GenericMapper<Boat, BoatDto> {

    BoatMapper INSTANCE = Mappers.getMapper(BoatMapper.class);

    // @Mapping(target = "owner", ignore = true)
    BoatDto entityToDto(Boat boat);

    Boat dtoToEntity(BoatDto boatDto);

    List<BoatDto> entitiesToDtos(List<Boat> boatList);

    List<Boat> dtosToEntities(List<BoatDto> boatDtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateEntityFromBoatDto(BoatDto boatDto, @MappingTarget Boat boat);

}