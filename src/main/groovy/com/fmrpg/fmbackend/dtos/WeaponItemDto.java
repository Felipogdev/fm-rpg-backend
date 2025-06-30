package com.fmrpg.fmbackend.dtos;

import com.fmrpg.fmbackend.entities.WeaponGroup;
import com.fmrpg.fmbackend.enums.DamageType;

import java.util.List;

public record WeaponItemDto(
        String name,
        Integer weight,
        Integer cost,
        String description,
        List<Integer> diceQuantity,
        List<Integer> diceValue,
        Integer critMargin,
        DamageType damageType,
        String weaponGroup,
        String weaponProperties
) implements CharacterItemDto {
}
