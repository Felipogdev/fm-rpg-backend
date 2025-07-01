package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterStatusMapper {
    CharacterStatusResponseDto toDto(CharacterStatus characterStatus);
}
