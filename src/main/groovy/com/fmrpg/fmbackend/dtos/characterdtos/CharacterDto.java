package com.fmrpg.fmbackend.dtos.characterdtos;

public record CharacterDto(
        String name,
        String imageUrl,
        String characterClass,
        String characterOrigin
) { }
