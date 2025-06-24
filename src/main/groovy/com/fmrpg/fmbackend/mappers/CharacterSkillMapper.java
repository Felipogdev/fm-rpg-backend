package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.CharacterSkillDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterSkillResponseDto;
import com.fmrpg.fmbackend.entities.CharacterSkill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterSkillMapper {
    CharacterSkill toEntity (CharacterSkillDto dto);
    CharacterSkillResponseDto toDto(CharacterSkill characterEntity);
}
