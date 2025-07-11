package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CursedAbilityDto;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedAbility;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import com.fmrpg.fmbackend.services.CursedAbilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/characters/ability")
public class CursedAbilityController {

    private final CursedAbilityService cursedAbilityService;
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;

    public CursedAbilityController(CursedAbilityService cursedAbilityService, CharacterRepository characterRepository, UserRepository userRepository) {
        this.cursedAbilityService = cursedAbilityService;
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{characterId}")
    public ResponseEntity<CursedAbility> createAbility(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @PathVariable("characterId")UUID characterId
            ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(cursedAbilityService.createAbility(oAuth2User,character));
    }

    @PatchMapping("/{characterId}/{abilityId}")
    public ResponseEntity<CursedAbility> updateAbility(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @RequestBody CursedAbilityDto dto,
            @PathVariable("characterId")UUID characterId,
            @PathVariable("abilityId") Long abilityId
    ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(cursedAbilityService.updateAbility(oAuth2User,character,dto, abilityId));
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<List<CursedAbility>> getAbilities(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @PathVariable("characterId")UUID characterId
    ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(cursedAbilityService.getAbilities(oAuth2User,character));
    }


}
