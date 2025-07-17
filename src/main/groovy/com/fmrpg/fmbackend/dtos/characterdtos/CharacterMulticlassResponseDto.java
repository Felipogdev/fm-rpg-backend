package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;

public record CharacterMulticlassResponseDto(
        CharacterClass characterClass,
        Integer level
) {
}
