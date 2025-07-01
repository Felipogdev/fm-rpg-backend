package com.fmrpg.fmbackend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WeaponItemDto.class, name = "weapon"),
        @JsonSubTypes.Type(value = ShieldItemDto.class, name = "shield"),
        @JsonSubTypes.Type(value = UniformItemDto.class, name = "uniform"),
        @JsonSubTypes.Type(value = SpecialItemDto.class, name="specialItem")
})
public sealed interface CharacterItemDto permits WeaponItemDto, ShieldItemDto, UniformItemDto, SpecialItemDto {
}
