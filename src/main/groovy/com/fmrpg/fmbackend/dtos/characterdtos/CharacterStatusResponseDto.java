package com.fmrpg.fmbackend.dtos.characterdtos;


import java.util.List;

public record CharacterStatusResponseDto(
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
        Integer soulPoint,
        List<CharacterSkillResponseDto> skills
) {
}
