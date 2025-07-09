package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;

public record CharacterMulticlassResponseDto(
        CharacterClass characterClass,
        Integer level
) {
}
