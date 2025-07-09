package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterMulticlass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterOrigin;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;

import java.util.List;
import java.util.UUID;

public record CharacterResponseDto(
        //TODO: CharacterOrigin and Multiclass not working
        UUID publicId,
        String name,
        String imageUrl,
        CharacterOrigin characterOrigin,
        List<CharacterMulticlass> characterMulticlass,
        String description,
        String grade,
        CharacterStatusResponseDto status,
        CursedTechnique technique
        ) {
}
