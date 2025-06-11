package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;


import com.fmrpg.fmbackend.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService
                                ) {
        this.characterService = characterService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CharacterEntity> createCharacter(@PathVariable ("userId") UUID userId, @RequestBody CharacterDto dto) {
        CharacterEntity created = characterService.createCharacter(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CharacterEntity>> getCharacters(@PathVariable("id") UUID id) {
        List<CharacterEntity> characters = characterService.getAllCharactersFromUser(id);
        return ResponseEntity.ok(characters);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CharacterEntity> updateCharacter(@PathVariable("id") UUID id, @RequestBody UpdateCharacterDto dto) {
        CharacterEntity updated = characterService.updateCharacter(id, dto);
        return ResponseEntity.ok(updated);
    }

}
