package com.fmrpg.fmbackend.facades;


import com.fmrpg.fmbackend.dtos.characterdtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.CharacterResponseMapper;
import com.fmrpg.fmbackend.services.CharacterService;
import com.fmrpg.fmbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CharacterFacade {

    private final CharacterService characterService;
    private final UserService userService;
    private final CharacterResponseMapper characterResponseMapper;

    public CharacterFacade(CharacterService characterService,
                           UserService userService,
                           CharacterResponseMapper characterResponseMapper) {
        this.characterService = characterService;
        this.userService = userService;
        this.characterResponseMapper = characterResponseMapper;
    }

    public List<CharacterResponseDto> getCharactersFromUser(OAuth2User oauth2User) {
        User user = userService.validateUser(oauth2User);
        return user.getCharacters().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CharacterResponseDto createCharacter(OAuth2User oauth2User, CharacterDto dto) {
        User user = userService.validateUser(oauth2User);
        CharacterEntity created = characterService.createCharacter(user.getOauthId(), dto);
        return toDto(created);
    }

    public CharacterResponseDto updateCharacter(OAuth2User oauth2User, UUID characterId, UpdateCharacterDto dto) {
        User user = userService.validateUser(oauth2User);
        validateOwnership(user, characterId);
        CharacterEntity updated = characterService.updateCharacter(characterId, dto);
        return toDto(updated);
    }

    public void deleteCharacter(OAuth2User oauth2User, UUID characterId) {
        User user = userService.validateUser(oauth2User);
        validateOwnership(user, characterId);
        characterService.deleteCharacter(characterId);
    }

    private void validateOwnership(User user, UUID characterId) {
        boolean owns = user.getCharacters() != null &&
                user.getCharacters().stream().anyMatch(c -> c.getId().equals(characterId));
        if (!owns) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not own the character.");
        }
    }

    private CharacterResponseDto toDto(CharacterEntity entity) {
        return characterResponseMapper.toDto(entity);
    }
}

