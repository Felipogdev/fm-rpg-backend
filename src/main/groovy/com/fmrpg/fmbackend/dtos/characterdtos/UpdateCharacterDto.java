package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterOrigin;

public record UpdateCharacterDto(
        String name,
        String imageUrl,
        Long characterClass,
        Long characterOrigin,
        Integer level,
        String description
) {
}
