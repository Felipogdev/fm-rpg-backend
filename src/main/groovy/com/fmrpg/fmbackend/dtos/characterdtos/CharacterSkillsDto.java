package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.enums.SkillProficiency;

public record CharacterSkillsDto(
        Long id,
        SkillProficiency proficiency,
        Integer bonus
) {
}
