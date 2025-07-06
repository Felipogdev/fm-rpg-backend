package com.fmrpg.fmbackend.dtos;

import com.fmrpg.fmbackend.entities.techniquepkg.CursedAbility;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;

import java.util.List;

public record CursedTechniqueDto(
        String name,
        String description
) {
}
