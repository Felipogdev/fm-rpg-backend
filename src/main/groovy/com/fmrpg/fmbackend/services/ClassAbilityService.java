package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.ClassAbilityDto;
import com.fmrpg.fmbackend.dtos.ClassAbilityResponseDto;
import com.fmrpg.fmbackend.dtos.OAuth2UserDto;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterMulticlass;
import com.fmrpg.fmbackend.entities.characterpkg.ClassAbility;
import com.fmrpg.fmbackend.mappers.ClassAbilityMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.ClassAbilityRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassAbilityService {

    private final ClassAbilityRepository classAbilityRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CharacterRepository characterEntityRepository;
    private final ClassAbilityMapper classAbilityMapper;

    public ClassAbilityService(ClassAbilityRepository classAbilityRepository, UserService userService, UserRepository userRepository, CharacterRepository characterEntityRepository, ClassAbilityMapper classAbilityMapper) {
        this.classAbilityRepository = classAbilityRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.characterEntityRepository = characterEntityRepository;
        this.classAbilityMapper = classAbilityMapper;
    }

    public ClassAbilityResponseDto addClassAbilityToCharacter(CharacterEntity character, ClassAbilityDto dto, OAuth2User oAuth2User) {
        User user = userRepository.findByGoogleId(oAuth2User.getName())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userService.isCharacterFromuser(user, character);

        ClassAbility ability = classAbilityRepository.findById(dto.id())
            .orElseThrow(() -> new EntityNotFoundException("Class ability not found"));

        List<CharacterClass> characterClasses = character.getCharacterMulticlass().stream()
            .map(CharacterMulticlass::getCharacterClass)
            .toList();

        if (!characterClasses.contains(ability.getAllowedClass())) {
            throw new EntityNotFoundException("Character does not have the required class for this ability");
        }

        if (character.getCharacterMulticlass().stream()
            .anyMatch(multiclass -> multiclass.getCharacterAbilities().contains(ability))) {
            throw new EntityNotFoundException("Character already has this ability");
        }

        character.getCharacterMulticlass().getFirst().getCharacterAbilities().add(ability);
        classAbilityRepository.save(ability);
        characterEntityRepository.save(character);

        return classAbilityMapper.toResponse(ability);
    }

    public List<ClassAbilityResponseDto> getCharacterAbilities(CharacterEntity character, OAuth2User oAuth2User) {
        User user = userRepository.findByGoogleId(oAuth2User.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userService.isCharacterFromuser(user, character);

        return character.getCharacterMulticlass().stream()
            .flatMap(multiclass -> multiclass.getCharacterAbilities().stream())
            .distinct()
            .map(classAbilityMapper::toResponse)
            .toList();
    }

    public void removeClassAbilityFromCharacter(CharacterEntity character, ClassAbility ability, OAuth2User oAuth2User) {
        User user = userRepository.findByGoogleId(oAuth2User.getName())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userService.isCharacterFromuser(user, character);

        if (!character.getCharacterMulticlass().getFirst().getCharacterAbilities().contains(ability)) {
            throw new EntityNotFoundException("Character does not have this ability");
        }

        character.getCharacterMulticlass().getFirst().getCharacterAbilities().remove(ability);
        classAbilityRepository.save(ability);
        characterEntityRepository.save(character);
    }

}
