package com.fmrpg.fmbackend.dtos;

public record ClassAbilityResponseDto(
        String name,
        String description,
        Integer abilityLevel,
        String requirement
) {
}
