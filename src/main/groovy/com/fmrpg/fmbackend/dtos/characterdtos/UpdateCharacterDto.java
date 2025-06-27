package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.CharacterClass;
import com.fmrpg.fmbackend.entities.CharacterOrigin;

public record UpdateCharacterDto(
        String name,
        String imageUrl,
        CharacterClass characterClass,
        CharacterOrigin characterOrigin,
        Integer level,
        String description
) {
}
