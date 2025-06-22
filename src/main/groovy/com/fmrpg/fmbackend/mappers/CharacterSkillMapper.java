package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.CharacterSKillDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterSkillResponseDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterSkill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterSkillMapper {
    CharacterSkill toEntity (CharacterSKillDto dto);
    CharacterSkillResponseDto toDto(CharacterSkill characterEntity);
}
