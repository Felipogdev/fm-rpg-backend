package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.Skills;
import com.fmrpg.fmbackend.enums.SkillProficiency;

public record CharacterSkillResponseDto(
        Skills skill,
        SkillProficiency proficiency,
        Integer bonus
) {
}
