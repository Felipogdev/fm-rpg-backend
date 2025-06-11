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
import java.util.stream.Stream;

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

            return ResponseEntity.ok(user.getCharacters());
    }


    @PostMapping
    public ResponseEntity<CharacterEntity> createCharacter(@AuthenticationPrincipal OAuth2User oauth2User, @RequestBody CharacterDto dto) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userService.findUserByOauthId(oauth2User.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CharacterEntity created = characterService.createCharacter(oauth2User.getName(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }



    @PatchMapping("/{characterId}")
    public ResponseEntity<CharacterEntity> updateCharacter(@AuthenticationPrincipal OAuth2User oauth2User, @PathVariable("characterId") UUID characterId, @RequestBody UpdateCharacterDto dto) {

        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (characterId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userService.findUserByOauthId(oauth2User.getName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean ownsCharacter = user.getCharacters() != null && user.getCharacters().stream()
                .anyMatch(character -> character.getId().equals(characterId));

        if (!ownsCharacter) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CharacterEntity updated = characterService.updateCharacter(characterId, dto);

        return ResponseEntity.ok(updated);
    }

}
