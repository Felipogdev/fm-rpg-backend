package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface CharacterMapper {

    @Mapping(target = "name", expression = "java(sanitize(createCharacterDto.name()))")
    @Mapping(target = "description", expression = "java(sanitize(createCharacterDto.description()))")
    @Mapping(target = "lore", expression = "java(sanitize(createCharacterDto.lore()))")
    @Mapping(target = "goal", expression = "java(sanitize(createCharacterDto.goal()))")
    @Mapping(target = "appearance", expression = "java(sanitize(createCharacterDto.appearance()))")

    CharacterEntity createToEntity(CreateCharacterDto createCharacterDto);
    CharacterResponseDto toResponse(CharacterEntity characterEntity);

    CharacterEntity toEntity(CharacterDto characterDto);
    default String sanitize(String input) {
        if (input == null) return null;
        return input.replaceAll("[<>]", "");
    }

}
