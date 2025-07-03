package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.characterdtos.UpdateCharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterOrigin;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;
import com.fmrpg.fmbackend.mappers.CharacterMapper;
import com.fmrpg.fmbackend.repositories.CharacterClassRepository;
import com.fmrpg.fmbackend.repositories.CharacterOriginRepository;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterStatusService characterStatusService;
    private final CharacterClassRepository characterClassRepository;
    private final CharacterOriginRepository characterOriginRepository;
    private final CursedTechniqueService cursedTechniqueService;

    public CharacterService(CharacterRepository characterRepository,
                            CharacterMapper characterMapper,
                            UserRepository userRepository,
                            UserService userService,
                            CharacterStatusService characterStatusService,
                            CharacterClassRepository characterClassRepository,
                            CharacterOriginRepository characterOriginRepository, CursedTechniqueService cursedTechniqueService
                            ) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.userRepository = userRepository;
        this.userService = userService;
        this.characterStatusService = characterStatusService;
        this.characterClassRepository = characterClassRepository;
        this.characterOriginRepository = characterOriginRepository;
        this.cursedTechniqueService = cursedTechniqueService;
    }

    public CharacterEntity createCharacter(String googleId, CreateCharacterDto dto) {
        if (dto == null || googleId == null) {
            throw new IllegalArgumentException("CharacterDto or UserId cannot be null");
        }

        User user = userRepository.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        CharacterEntity character = characterMapper.createToEntity(dto);

        CharacterClass characterClass = characterClassRepository.findById(dto.characterClassId())
                .orElseThrow(() -> new IllegalArgumentException("Character class not found"));

        CharacterOrigin characterOrigin = characterOriginRepository.findById(dto.characterOriginId())
                .orElseThrow(() -> new IllegalArgumentException("Character origin not found"));

        character.setUser(user);
        character.setCharacterClass(characterClass);
        character.setCharacterOrigin(characterOrigin);
        userService.addCharacterToUser(character);


        int[] statusFromDto = new int[6];


        statusFromDto[0] = dto.strength() != null ? dto.strength() : 8;
        statusFromDto[1] = dto.constitution() != null ? dto.constitution() : 8;
        statusFromDto[2] = dto.intelligence() != null ? dto.intelligence() : 8;
        statusFromDto[3] = dto.dexterity() != null ? dto.dexterity() : 8;
        statusFromDto[4] = dto.wisdom() != null ? dto.wisdom() : 8;
        statusFromDto[5] = dto.charisma() != null ? dto.charisma() : 8;


        cursedTechniqueService.createTechnique(character);
        characterRepository.save(character);
        characterStatusService.createCharacterStatus(character, statusFromDto);

        return character;
    }

    public List<CharacterEntity> getAllCharactersFromUser(Long privateId) {

        User user = userRepository.findByPrivateId(privateId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID  does not exist"));

        return user.getCharacters();
    }


    public CharacterEntity updateCharacter(CharacterEntity character, UpdateCharacterDto dto) {
        if (dto == null || character == null) {
            throw new IllegalArgumentException("DTO or character cannot be null");
        }

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

    public User getUserByCharacterId(Long privateId) {
        CharacterEntity character = characterRepository.findByPrivateId(privateId);
        return character.getUser();
    }

    public void deleteCharacter(CharacterEntity character) {
        User user = character.getUser();
        user.getCharacters().remove(character);
        characterRepository.delete(character);
    }

    public boolean isCharacterOwnedByUser(User user, CharacterEntity character) {
        if (user == null || character == null) {
            return false;
        }

        return user.getCharacters() != null &&
                user.getCharacters().stream().anyMatch(c -> c.getPrivateId().equals(character.getPrivateId()));
    }

    public void validateOwnership(User user, CharacterEntity character) {
        if (!isCharacterOwnedByUser(user, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not own this character.");
        }
    }

    public boolean isItemFromCharacter(CharacterEntity character, CharacterItem item) {
        if (character == null || item == null || character.getInventory() == null) return false;
        return character.getInventory().contains(item);
    }

    public boolean isTechniqueFromCharacter(CursedTechnique technique, CharacterEntity character) {
        if (character == null || technique == null) return false;
        return technique.equals(character.getTechnique());
    }



}
