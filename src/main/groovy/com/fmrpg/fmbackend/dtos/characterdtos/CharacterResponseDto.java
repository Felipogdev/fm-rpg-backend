package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.dtos.FeatsResponseDto;
import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;

import java.util.List;
import java.util.UUID;

public record CharacterResponseDto(
        UUID publicId,
        String name,
        String imageUrl,
        CharacterOriginResponseDto characterOrigin,
        List<CharacterMulticlassResponseDto> characterMulticlass,
        String description,
        String grade,
        CharacterStatusResponseDto status,
        CursedTechnique technique,
        List<FeatsResponseDto> feats,
        List<CharacterItem> inventory
        ) {
}
