package feiticeiros.example.fmbackend.character;

import lombok.Data;

import java.util.UUID;

public record CharacterDTO(
        UUID userId,
        String name,
        String origin,
        Integer level,
        String character_class,
        String image
) { }
