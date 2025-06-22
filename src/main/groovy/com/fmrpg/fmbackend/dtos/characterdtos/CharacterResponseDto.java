package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;

import java.util.UUID;

public record CharacterResponseDto(
        UUID publicId,
        String name,
        String imageUrl,
        CharacterOrigin characterOrigin,
        CharacterClass characterClass,
        String description,
        String grade,
        CharacterStatusResponseDto status
        ) {
}
