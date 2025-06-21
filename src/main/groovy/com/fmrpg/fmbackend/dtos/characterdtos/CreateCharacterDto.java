package com.fmrpg.fmbackend.dtos.characterdtos;

import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;

public record CreateCharacterDto(
        Integer constitution,
        Integer intelligence,
        Integer dexterity,
        Integer strength,
        Integer wisdom,
        Integer charisma,
        CharacterClass characterClass,
        CharacterOrigin characterOrigin,
        String name,
        String description,
        String appearance,
        String lore,
        String goal
)
{ }
