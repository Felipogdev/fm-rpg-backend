package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;


import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.services.CharacterService;
import com.fmrpg.fmbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final UserService userService;

    public CharacterController(CharacterService characterService,
                                UserService userService
                                ) {
        this.characterService = characterService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<List<CharacterEntity>> getCharactersForAuthenticatedUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String oauthId = oauth2User.getName();

        User user = userService.findUserByOauthId(oauthId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        List<CharacterEntity> characters = user.getCharacters();

        return ResponseEntity.ok(characters);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CharacterEntity> createCharacter(@PathVariable ("userId") UUID userId, @RequestBody CharacterDto dto) {
        CharacterEntity created = characterService.createCharacter(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<CharacterEntity> updateCharacter(@PathVariable("id") UUID id, @RequestBody UpdateCharacterDto dto) {
        CharacterEntity updated = characterService.updateCharacter(id, dto);
        return ResponseEntity.ok(updated);
    }

}
