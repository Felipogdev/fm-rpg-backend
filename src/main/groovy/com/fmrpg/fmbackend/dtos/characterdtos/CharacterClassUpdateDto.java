package com.fmrpg.fmbackend.dtos.characterdtos;

public record CharacterClassUpdateDto(
        Long classId,
        Integer levelChange
) {
}
