package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterFeat;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.FeatService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feats")
public class CharacterFeatController {

    private final FeatService featService;
    private final CharacterRepository characterRepository;

    public CharacterFeatController(FeatService featService, CharacterRepository characterRepository) {
        this.featService = featService;
        this.characterRepository = characterRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterFeat>> getAllFeats() {
        return ResponseEntity.ok(featService.getAllFeats());
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<List<CharacterFeat>> getCharacterFeats(
            @PathVariable(name = "characterId") UUID characterId,
            @AuthenticationPrincipal OAuth2User oAuth2User
            ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(featService.getCharacterFeats(oAuth2User, character));
    }

    @PostMapping("/{characterId}/{featId}")
    public ResponseEntity<CharacterFeat> addFeat(
            @PathVariable(name = "characterId") UUID characterId,
            @PathVariable(name ="featId") Long featId,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(featService.giveCharacterFeat(oAuth2User, character, featId));
    }

    @DeleteMapping("/{characterId}/{featId}")
    public ResponseEntity removeFeat(
            @PathVariable(name = "characterId") UUID characterId,
            @PathVariable(name ="featId") Long featId,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);
         featService.removeCharacterFeat(oAuth2User, character, featId);
         return ResponseEntity.ok().build();
    }


}
