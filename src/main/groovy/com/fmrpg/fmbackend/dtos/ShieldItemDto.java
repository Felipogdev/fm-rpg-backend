package com.fmrpg.fmbackend.dtos;

import java.util.List;

public record ShieldItemDto(
        String name,
        Integer weight,
        Integer cost,
        String description,
        Integer bonusArmor,
        Integer penalty
) implements CharacterItemDto {
}
