package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CursedTechniqueDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.CursedTechniqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/characters/cursedtechnique")
public class CursedTechniqueController {

    private final CursedTechniqueService cursedTechniqueService;
    private final CharacterRepository characterRepository;

    public CursedTechniqueController(CursedTechniqueService cursedTechniqueService, CharacterRepository characterRepository) {
        this.cursedTechniqueService = cursedTechniqueService;
        this.characterRepository = characterRepository;
    }


    @PatchMapping("/{characterId}")
    public ResponseEntity<CursedTechniqueDto> updateTechnique(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @PathVariable("characterId") UUID characterId,
            @RequestBody CursedTechniqueDto dto) {


        CharacterEntity character = characterRepository.findByPublicId(characterId);
        cursedTechniqueService.updateTechnique(oAuth2User, character, dto);
        return ResponseEntity.ok(dto);
    }


}
