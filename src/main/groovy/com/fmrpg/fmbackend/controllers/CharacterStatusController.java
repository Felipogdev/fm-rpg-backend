package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CharacterStatusDto;
import com.fmrpg.fmbackend.services.CharacterStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/character/status")
public class CharacterStatusController {

    private final CharacterStatusService characterStatusService;

    public CharacterStatusController(CharacterStatusService characterStatusService) {
        this.characterStatusService = characterStatusService;
    }

    @PatchMapping("/{characterId}")
    public CharacterStatusService updateCharacterStatus(@PathVariable ("characterId") UUID characterId, @RequestBody CharacterStatusDto dto) {
        if (dto == null || characterId == null) {
            throw new IllegalArgumentException("CharacterStatusDto or CharacterId cannot be null");
        }

        return characterStatusService.updateCharacterStatus(characterId, dto);
    }


}
