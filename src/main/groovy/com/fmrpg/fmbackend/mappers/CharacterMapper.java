package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CharacterMapper {
    CharacterEntity toEntity(CharacterDto characterDto);
}
