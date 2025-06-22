package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterStatusDto {
    CharacterStatusResponseDto toDto(CharacterStatus characterStatus);
}
