package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterClassDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterMulticlassResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterMulticlass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMulticlassMapper {

    @Mapping(source = "characterClass", target = "characterClass")
    CharacterMulticlassResponseDto toResponse(CharacterMulticlass characterMulticlass);

    CharacterClassDto toDto(CharacterClass characterClass);
}
