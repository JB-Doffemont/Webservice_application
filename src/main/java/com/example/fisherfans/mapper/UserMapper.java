package com.example.fisherfans.mapper;

import com.example.fisherfans.dto.UserDto;
import com.example.fisherfans.entity.User;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDto> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userdto);

    List<UserDto> entitiesToDtos(List<User> users);

    List<User> dtosToEntities(List<UserDto> userDtos);

}