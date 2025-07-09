package com.fmrpg.fmbackend.dtos.characterdtos;

public record CreateCharacterStatusDto(
        Integer strength,
        Integer constitution,
        Integer intelligence,
        Integer dexterity,
        Integer wisdom,
        Integer charisma
) {
}
