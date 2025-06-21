package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;

public record CharacterResponseDto(
        String name,
        String imageUrl,
        CharacterOrigin characterOrigin,
        CharacterClass characterClass,
        String description,
        String grade,
        CharacterStatus status
        ) {
}
