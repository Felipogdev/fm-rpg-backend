package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;


import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.facades.CharacterFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterFacade characterFacade;

    public CharacterController(CharacterFacade characterFacade) {
        this.characterFacade = characterFacade;
    }

    @GetMapping("/me")
    public ResponseEntity<List<CharacterResponseDto>> getCharactersFromAuthenticatedUser(
            @AuthenticationPrincipal OAuth2User oauth2User) {
        List<CharacterResponseDto> characters = characterFacade.getCharactersFromUser(oauth2User);
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDto> createCharacter(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @RequestBody CharacterDto dto) {
        CharacterResponseDto created = characterFacade.createCharacter(oauth2User, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{characterId}")
    public ResponseEntity<CharacterResponseDto> updateCharacter(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @PathVariable("characterId") UUID characterId,
            @RequestBody UpdateCharacterDto dto) {
        CharacterResponseDto updated = characterFacade.updateCharacter(oauth2User, characterId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<Map<String, String>> deleteCharacter(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @PathVariable("characterId") UUID characterId) {
        characterFacade.deleteCharacter(oauth2User, characterId);
        return ResponseEntity.ok(Collections.singletonMap("message", "Character Deleted"));
    }
}
