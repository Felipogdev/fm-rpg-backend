package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CharacterMapper {
    CharacterEntity createToEntity(CreateCharacterDto createCharacterDto);
    CharacterEntity toEntity(CharacterDto characterDto);
}
