package com.fmrpg.fmbackend.facades;


import com.fmrpg.fmbackend.dtos.characterdtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.CharacterResponseMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.CharacterService;
import com.fmrpg.fmbackend.services.UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CharacterFacade {

    private final CharacterService characterService;
    private final UserService userService;
    private final CharacterResponseMapper characterResponseMapper;
    private final CharacterRepository characterRepository;

    public CharacterFacade(CharacterService characterService,
                           UserService userService,
                           CharacterResponseMapper characterResponseMapper, CharacterRepository characterRepository) {
        this.characterService = characterService;
        this.userService = userService;
        this.characterResponseMapper = characterResponseMapper;
        this.characterRepository = characterRepository;
    }

    public List<CharacterResponseDto> getCharactersFromUser(OAuth2User oauth2User) {
        User user = userService.validateUser(oauth2User);
        return user.getCharacters().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CharacterResponseDto createCharacter(OAuth2User oauth2User, CreateCharacterDto dto) {
        User user = userService.validateUser(oauth2User);
        CharacterEntity created = characterService.createCharacter(user.getGoogleId(), dto);
        return toDto(created);
    }

    public CharacterResponseDto updateCharacter(OAuth2User oauth2User, UUID publicId, UpdateCharacterDto dto) {
        User user = userService.validateUser(oauth2User);
        CharacterEntity character = characterRepository.findByPublicId(publicId);
        characterService.validateOwnership(user, character);

        CharacterEntity updated = characterService.updateCharacter(character, dto);

        return toDto(updated);
    }


    public void deleteCharacter(OAuth2User oauth2User, UUID characterId) {
        User user = userService.validateUser(oauth2User);
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        characterService.isCharacterOwnedByUser(user, character);
        characterService.deleteCharacter(character);
    }

    public CharacterResponseDto getCharacterInfoFromCharacterId(OAuth2User oauth2User, UUID characterId) {
        User user = userService.validateUser(oauth2User);
        CharacterEntity character = characterRepository.findByPublicId(characterId);
        characterService.validateOwnership(user, character);
        return characterResponseMapper.toDto(character);
    }


    private CharacterResponseDto toDto(CharacterEntity entity) {
        return characterResponseMapper.toDto(entity);
    }
}

