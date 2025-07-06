package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;

import java.util.UUID;

public record CharacterResponseDto(
        UUID publicId,
        String name,
        String imageUrl,
        CharacterClassResponseDto characterOrigin,
        CharacterOriginResponseDto characterClass,
        String description,
        String grade,
        CharacterStatusResponseDto status,
        CursedTechnique technique
        ) {
}
