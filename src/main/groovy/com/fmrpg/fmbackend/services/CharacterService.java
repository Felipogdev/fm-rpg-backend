package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.dtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.CharacterMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    public CharacterService(CharacterRepository characterRepository,
                            CharacterMapper characterMapper,
                            UserRepository userRepository,
                            UserService userService) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.userRepository = userRepository;
        this.userService = userService;

    }

    public CharacterEntity createCharacter(CharacterDto characterDto) {
        if (characterDto == null || characterDto.userId() == null) {
            throw new IllegalArgumentException("CharacterDto or UserId cannot be null");
        }

        User user = userRepository.findById(characterDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + characterDto.userId() + " does not exist"));

        CharacterEntity character = characterMapper.toEntity(characterDto);

        character.setUser(user);

        userService.addCharacterToUser(character);

        return characterRepository.save(character);
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
}
