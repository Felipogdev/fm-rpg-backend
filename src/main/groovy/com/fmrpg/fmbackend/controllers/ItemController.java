package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.ItemDto;
import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/character/items/")
public class ItemController {

    private final CharacterRepository characterRepository;
    private final ItemService itemService;

    public ItemController(CharacterRepository characterRepository, ItemService itemService) {
        this.characterRepository = characterRepository;
        this.itemService = itemService;
    }


    @PostMapping("/{characterId}")
    public ResponseEntity<CharacterItem> CreateItem(@PathVariable("characterId") UUID characterId,
                                                    @AuthenticationPrincipal OAuth2User oAuth2User,
                                                    @RequestBody ItemDto dto) {

        CharacterEntity character = characterRepository.findByPublicId(characterId);
        return ResponseEntity.ok(itemService.createItem(oAuth2User ,character, dto.name()));
    }


}
