package com.fmrpg.fmbackend.dtos.characterdtos;

public record CharacterMulticlassResponseDto(
        CharacterClassResponseDto characterClass,
        Integer level
) {
}
