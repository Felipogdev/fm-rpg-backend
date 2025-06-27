package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.entities.CharacterClass;
import com.fmrpg.fmbackend.entities.CharacterOrigin;

public record CreateCharacterDto(
        Integer constitution,
        Integer intelligence,
        Integer dexterity,
        Integer strength,
        Integer wisdom,
        Integer charisma,
        Long characterClassId,
        Long characterOriginId,
        String name,
        String description,
        String appearance,
        String lore,
        String goal
)
{ }
