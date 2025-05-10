package feiticeiros.example.fmbackend.character;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

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
