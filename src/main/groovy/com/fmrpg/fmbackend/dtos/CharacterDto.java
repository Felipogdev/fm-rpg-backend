package com.fmrpg.fmbackend.dtos;

import com.fmrpg.fmbackend.entities.User;

import java.util.UUID;

public record CharacterDto(
        String name,
        String imageUrl,
        String characterClass,
        String characterOrigin
) { }
