package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.CharacterMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterStatusService characterStatusService;

    public CharacterService(CharacterRepository characterRepository,
                            CharacterMapper characterMapper,
                            UserRepository userRepository,
                            UserService userService,
                            CharacterStatusService characterStatusService) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.userRepository = userRepository;
        this.userService = userService;
        this.characterStatusService = characterStatusService;

    }

    public CharacterEntity createCharacter(String oauthId, CreateCharacterDto dto) {
        if (dto == null || oauthId == null) {
            throw new IllegalArgumentException("CharacterDto or UserId cannot be null");
        }

        User user = userRepository.findByOauthId(oauthId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        CharacterEntity character = characterMapper.createToEntity(dto);

        character.setUser(user);
        userService.addCharacterToUser(character);


        int[] statusFromDto = new int[6];


        statusFromDto[1] = dto.strength() != null ? dto.strength() : 8;
        statusFromDto[1] = dto.constitution() != null ? dto.constitution() : 8;
        statusFromDto[2] = dto.intelligence() != null ? dto.intelligence() : 8;
        statusFromDto[3] = dto.dexterity() != null ? dto.dexterity() : 8;
        statusFromDto[4] = dto.wisdom() != null ? dto.wisdom() : 8;
        statusFromDto[5] = dto.charisma() != null ? dto.charisma() : 8;



        characterRepository.save(character);
        characterStatusService.crateCharacterStatus(character, statusFromDto);

        return character;
    }

    public List<CharacterEntity> getAllCharactersFromUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " does not exist"));

        return user.getCharacters();
    }

    public CharacterEntity getCharacterById(UUID id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Character with ID " + id + " does not exist"));
    }

    public CharacterEntity updateCharacter(UUID id, UpdateCharacterDto dto) {

        if (dto == null || id == null) {
            throw new IllegalArgumentException("Json or ID cannot be null");
        }

        CharacterEntity character = getCharacterById(id);

        if (dto.name() != null) {
            character.setName(dto.name());
        }

        if (dto.imageUrl() != null) {
            character.setImageUrl(dto.imageUrl());
        }

        if (dto.characterClass() != null) {
            character.setCharacterClass(dto.characterClass());
        }

        if (dto.characterOrigin() != null) {
            character.setCharacterOrigin(dto.characterOrigin());
        }

        if (dto.level() != null) {
            character.setLevel(dto.level());
        }

        if (dto.description() != null) {
            character.setDescription(dto.description());
        }

        return characterRepository.save(character);
    }

    public User getUserByCharacterId(UUID characterId) {
        CharacterEntity character = this.getCharacterById(characterId);
        return character.getUser();
    }

    public void deleteCharacter(UUID id) {
        CharacterEntity character = getCharacterById(id);
        User user = this.getUserByCharacterId(id);

        user.getCharacters().remove(character);

        characterRepository.delete(character);
    }

    public boolean isCharacterOwnedByUser(User user, CharacterEntity character) {
        if (user == null || character == null) {
            return false;
        }

        return user.getCharacters() != null &&
                user.getCharacters().stream().anyMatch(c -> c.getId().equals(character.getId()));
    }

    public void validateOwnership(User user, CharacterEntity character) {
        if (!isCharacterOwnedByUser(user, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not own this character.");
        }
    }


}
