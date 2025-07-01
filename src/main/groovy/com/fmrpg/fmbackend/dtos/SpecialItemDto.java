package com.fmrpg.fmbackend.dtos;

import com.fmrpg.fmbackend.enums.SpecialItemCategory;

public record SpecialItemDto(
        String name,
        String description,
        Integer weight,
        Integer cost,
        SpecialItemCategory specialItemCategory
) implements CharacterItemDto {
}
