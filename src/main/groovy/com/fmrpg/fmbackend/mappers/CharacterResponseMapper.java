package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterResponseMapper {
    CharacterResponseDto toDto(CharacterEntity characterEntity);
}
