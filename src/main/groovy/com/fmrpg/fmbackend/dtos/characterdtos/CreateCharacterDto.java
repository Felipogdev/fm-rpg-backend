package com.fmrpg.fmbackend.dtos.characterdtos;

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
