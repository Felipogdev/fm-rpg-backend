package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.CharacterMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
}
