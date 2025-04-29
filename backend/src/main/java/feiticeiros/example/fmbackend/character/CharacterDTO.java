package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.character.enums.CharacterClasses;
import feiticeiros.example.fmbackend.character.enums.CharacterOrigin;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.UUID;

public record CharacterDTO(

        UUID userId,

        String name,

        @Enumerated(EnumType.STRING)
        CharacterOrigin origin,

        @Min(0)
        @Max(30)
        Integer level,

        @Enumerated(EnumType.STRING)
        CharacterClasses character_class,

        String image
) { }
