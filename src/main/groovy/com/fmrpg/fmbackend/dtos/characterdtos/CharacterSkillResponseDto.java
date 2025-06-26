package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.enums.AttributeType;
import com.fmrpg.fmbackend.enums.SkillProficiency;

public record CharacterSkillResponseDto(
         String name,
         AttributeType relatedAttribute,
         SkillProficiency proficiency
) {
}
