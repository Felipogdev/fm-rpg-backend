package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.ClassAbilityDto;
import com.fmrpg.fmbackend.dtos.ClassAbilityResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.ClassAbility;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.ClassAbilityRepository;
import com.fmrpg.fmbackend.services.ClassAbilityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/class-abilities")
public class ClassAbilityController {

    private final ClassAbilityService classAbilityService;
    private final CharacterRepository characterRepository;
    private final ClassAbilityRepository classAbilityRepository;

    public ClassAbilityController(ClassAbilityService classAbilityService, CharacterRepository characterRepository, ClassAbilityRepository classAbilityRepository) {
        this.classAbilityService = classAbilityService;
        this.characterRepository = characterRepository;
        this.classAbilityRepository = classAbilityRepository;
    }

    @PostMapping("/{characterId}")
    public ResponseEntity<ClassAbilityResponseDto> addClassAbility(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @PathVariable(name = "characterId") UUID characterId,
            @RequestBody ClassAbilityDto dto) {

        CharacterEntity character = characterRepository.findByPublicId(characterId);

        return ResponseEntity.ok(classAbilityService.addClassAbilityToCharacter(character, dto, oauth2User));
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<List<ClassAbilityResponseDto>> getCharacterAbiltiies(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @PathVariable(name = "characterId") UUID characterId) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);

    return ResponseEntity.ok(classAbilityService.getCharacterAbilities(character, oauth2User));
    }

    @DeleteMapping("/{characterId}/{abilityId}")
    public ResponseEntity<Void> removeClassAbility(
            @AuthenticationPrincipal OAuth2User oauth2User,
            @PathVariable(name = "characterId") UUID characterId,
            @PathVariable(name = "abilityId") Long abilityId) {

        CharacterEntity character = characterRepository.findByPublicId(characterId);
        ClassAbility ability = classAbilityRepository.findById(abilityId).orElseThrow(EntityNotFoundException::new);

        classAbilityService.removeClassAbilityFromCharacter(character, ability, oauth2User);
        return ResponseEntity.noContent().build();
    }
}
