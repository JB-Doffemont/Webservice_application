package com.example.fisherfans.mapper;

import java.util.List;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface BoatMapper {

    @Mapping(source = "owner", target = "ownerDto")
    BoatDto entityToDto(Boat boat);

    @Mapping(source = "ownerDto", target = "owner")
    Boat dtoToEntity(BoatDto boatDto);

    List<BoatDto> entitiesToDtos(List<Boat> boatList);

    List<Boat> dtosToEntities(List<BoatDto> boatDtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "ownerDto", target = "owner")
    public void updateEntityFromBoatDto(BoatDto boatDto, @MappingTarget Boat boat);

}