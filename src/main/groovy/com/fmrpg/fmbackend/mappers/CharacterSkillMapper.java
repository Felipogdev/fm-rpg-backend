package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterSkillResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterSkill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterSkillMapper {

    CharacterSkillResponseDto toDto(CharacterSkill characterEntity);
}
