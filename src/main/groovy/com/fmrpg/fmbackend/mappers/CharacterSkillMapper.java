package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterSkillResponseDto;
import com.fmrpg.fmbackend.entities.CharacterSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterSkillMapper {

    CharacterSkillResponseDto toDto(CharacterSkill characterEntity);
}
