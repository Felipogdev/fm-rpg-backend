package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterMulticlassResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterMulticlass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMulticlassMapper {
    CharacterMulticlassResponseDto toResponse(CharacterMulticlass characterMulticlass);
}
