package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;


import com.fmrpg.fmbackend.services.CharacterService;
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

    @PostMapping("/create")
    public CharacterEntity createCharacter(@RequestBody CharacterDto dto) {
        return characterService.createCharacter(dto);
    }

    @GetMapping("/{id}")
    public List<CharacterEntity> getCharacters(@PathVariable("id") UUID id) {
        return characterService.getAllCharactersFromUser(id);
    }

    @PatchMapping("/update/{id}")
    public CharacterEntity updateCharacter(@PathVariable("id") UUID id, @RequestBody UpdateCharacterDto dto) {
        return characterService.updateCharacter(id, dto);
    }

}
