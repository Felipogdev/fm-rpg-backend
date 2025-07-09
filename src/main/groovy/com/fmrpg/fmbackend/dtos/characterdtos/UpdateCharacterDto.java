package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterOrigin;

import java.util.List;

public record UpdateCharacterDto(
        String name,
        String imageUrl,
        List<CharacterClassUpdateDto> characterClasses,
        Long characterOrigin,
        Integer level,
        String description
) {
}
