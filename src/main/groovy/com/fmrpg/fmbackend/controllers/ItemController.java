package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.*;
import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PatchMapping("/{characterId}/{itemId}")
    public ResponseEntity<CharacterItem> updateItem(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @RequestBody CharacterItemDto dto,
            @PathVariable("characterId") UUID characterId,
            @PathVariable("itemId") Long itemId
    ) {

        CharacterEntity character = characterRepository.findByPublicId(characterId);

        if (dto instanceof  WeaponItemDto weaponDto) {
            return ResponseEntity.ok(itemService.updateWeapon(oAuth2User, character, weaponDto, itemId));
        }

        if (dto instanceof ShieldItemDto shieldDto) {
            return ResponseEntity.ok(itemService.updateShield(oAuth2User, character, shieldDto, itemId));
        }

        if (dto instanceof UniformItemDto uniformDto){
            return ResponseEntity.ok(itemService.updateUniform(oAuth2User, character, uniformDto, itemId));
        }

        if (dto instanceof SpecialItemDto specialItemDto) {
            return ResponseEntity.ok(itemService.updateSpecialItem(oAuth2User, character, specialItemDto, itemId));
        }

        throw new IllegalArgumentException("dto null");
    }

    @DeleteMapping("/{characterId}/{itemId}")
    public ResponseEntity<String> deleteItem(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @RequestBody CharacterItemDto dto,
            @PathVariable("characterId") UUID characterId,
            @PathVariable("itemId") Long itemId
    ) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);

        itemService.deleteItem(oAuth2User,character,itemId);
        return ResponseEntity.ok("Item deletado");
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<List<CharacterItem>> getInventory(
            @AuthenticationPrincipal OAuth2User oAuth2User,
    @PathVariable("characterId") UUID characterId) {
        CharacterEntity character = characterRepository.findByPublicId(characterId);

        return ResponseEntity.ok(itemService.showInventory(oAuth2User,character));
    }


}
