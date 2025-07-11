package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.services.CharacterStatusService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CharacterStatusResponseDto> updateCharacterStatus(@PathVariable ("characterId") UUID characterId,
                                                                            @RequestBody CharacterStatusDto dto) {

        return ResponseEntity.ok(characterStatusService.updateCharacterStatus(characterId, dto));
    }


}
