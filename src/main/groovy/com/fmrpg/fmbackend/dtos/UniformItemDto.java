package com.fmrpg.fmbackend.dtos;

public record UniformItemDto(
        String name,
        Integer weight,
        Integer cost,
        String description,
        Integer bonusArmor,
        Integer penalty
) implements CharacterItemDto {
}
