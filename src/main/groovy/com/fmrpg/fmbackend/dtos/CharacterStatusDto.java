package com.fmrpg.fmbackend.dtos;

public record CharacterStatusDto(
        Integer currentHp,
        Integer maxHp,
        Integer currentCursedEnergy,
        Integer maxCursedEnergy,
        Integer constitution,
        Integer intelligence,
        Integer dexterity,
        Integer strength,
        Integer wisdom,
        Integer charisma,
        Integer initiative,
        Integer movement,
        Integer armorClass,
        Integer soulPoint
) {
}
