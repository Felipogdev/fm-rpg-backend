package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterOriginResponseDto;
import com.fmrpg.fmbackend.entities.CharacterOrigin;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CharacterOriginMapper {

    CharacterOriginResponseDto toResponse(CharacterOrigin characterOrigin);
}
