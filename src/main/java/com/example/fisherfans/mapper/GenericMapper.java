package com.example.fisherfans.mapper;

import java.util.List;

public interface GenericMapper<E, D> {

    List<D> entitiesToDtos(List<E> entities);

    public D entityToDto(E entity);

    List<E> dtosToEntities(List<D> dtos);

    E dtoToEntity(D dto);
}
