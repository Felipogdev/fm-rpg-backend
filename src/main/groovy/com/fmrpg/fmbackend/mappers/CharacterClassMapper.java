package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterClassResponseDto;
import com.fmrpg.fmbackend.entities.CharacterClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterClassMapper {

    CharacterClassResponseDto toResponse(CharacterClass characterClass);
}
