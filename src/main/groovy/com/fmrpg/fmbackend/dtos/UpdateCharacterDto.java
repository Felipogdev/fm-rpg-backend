package com.fmrpg.fmbackend.dtos;

import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;

public record UpdateCharacterDto(
        String name,
        String imageUrl,
        CharacterClass characterClass,
        CharacterOrigin characterOrigin,
        Integer level,
        String description
) {
}
