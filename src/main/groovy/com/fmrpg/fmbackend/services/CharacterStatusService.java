package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CreateCharacterStatusDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterStatus;
import com.fmrpg.fmbackend.mappers.CharacterStatusMapper;
import com.fmrpg.fmbackend.repositories.CharacterStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class CharacterStatusService {

    private static final int STRENGTH = 0;
    private static final int CONSTITUTION = 1;
    private static final int INTELLIGENCE = 2;
    private static final int DEXTERITY = 3;
    private static final int WISDOM = 4;
    private static final int CHARISMA = 5;

    private final CharacterStatusRepository characterStatusRepository;
    private final CharacterSkillsService characterSkillsService;
    private final CharacterStatusMapper characterStatusMapper;

    public CharacterStatusService(
            CharacterStatusRepository characterStatusRepository,
            CharacterSkillsService characterSkillsService,
            CharacterStatusMapper characterStatusMapper) {
        this.characterStatusRepository = characterStatusRepository;
        this.characterSkillsService = characterSkillsService;
        this.characterStatusMapper = characterStatusMapper;
    }

    public void createCharacterStatus(CharacterEntity character, CreateCharacterStatusDto dto) {

        CharacterStatus status = new CharacterStatus(
                character,
                dto.strength(),
                dto.constitution(),
                dto.intelligence(),
                dto.dexterity(),
                dto.wisdom(),
                dto.charisma()
        );

        character.setStatus(status);
        status.setCharacter(character);

        setHpSoulCursedEnergyOnCreation(character);
        characterStatusRepository.save(status);
        characterSkillsService.createSkillsForCharacter(status);
    }

    public Integer getModifiers(Integer attributeValue) {
        if (attributeValue == null) {
            return 0;
        }
        return (int) Math.floor((attributeValue - 10) / 2.0);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public CharacterStatusResponseDto updateCharacterStatus(UUID characterPublicId, CharacterStatusDto dto) {

        CharacterStatus status = characterStatusRepository.findByCharacterPublicId(characterPublicId)
                .orElseThrow(() -> new EntityNotFoundException("CharacterStatus not found for character with id " + characterPublicId));

        if (dto.maxHp() != null) {
            if (Objects.equals(status.getMaxHp(), status.getCurrentHp())) {
                status.setMaxHp(dto.maxHp());
                status.setCurrentHp(dto.maxHp());
            } else {
                status.setMaxHp(dto.maxHp());
            }

            if (Objects.equals(status.getMaxHp(), status.getMaxSoulPoint())) {
                updateSoulPoints(status);
            }
        }

        updateIfNotNull(dto.currentHp(), status::setCurrentHp);

        if (dto.maxCursedEnergy() != null) {
            if (Objects.equals(status.getMaxCursedEnergy(), status.getCurrentCursedEnergy())) {
                status.setMaxCursedEnergy(dto.maxCursedEnergy());
                status.setCurrentCursedEnergy(dto.maxCursedEnergy());
            } else {
                status.setMaxCursedEnergy(dto.maxCursedEnergy());
            }
        }

        updateIfNotNull(dto.currentCursedEnergy(), status::setCurrentCursedEnergy);
        updateIfNotNull(dto.constitution(), status::setConstitution);
        updateIfNotNull(dto.intelligence(), status::setIntelligence);
        updateIfNotNull(dto.dexterity(), status::setDexterity);
        updateIfNotNull(dto.strength(), status::setStrength);
        updateIfNotNull(dto.wisdom(), status::setWisdom);
        updateIfNotNull(dto.charisma(), status::setCharisma);
        updateIfNotNull(dto.initiative(), status::setInitiative);
        updateIfNotNull(dto.movement(), status::setMovement);
        updateIfNotNull(dto.armorClass(), status::setDefense);
//        updateIfNotNull(dto.soulPoint(), status::setSoulPoint);
        //TODO: Soul points were changed in the new version of the book, so this needs to be updated

        characterStatusRepository.save(status);

        return characterStatusMapper.toDto(status);
    }

    private static final Map<String, Integer> BASE_HP_BY_CLASS = Map.of(
            "Lutador", 12,
            "Especialista em Combate", 12,
            "Especialista em Técnica", 10,
            "Controlador", 10,
            "Suporte", 10,
            "Restringido", 16
    );

    private void setHpOnCreation(CharacterEntity character) {
        CharacterClass characterClass = character.getCharacterMulticlass().get(0).getCharacterClass();

        if (characterClass == null) {
            throw new IllegalArgumentException("Classe de personagem não pode ser nula");
        }

        String className = characterClass.getName();
        Integer baseHp = BASE_HP_BY_CLASS.get(className);

        if (baseHp == null) {
            throw new IllegalArgumentException("Classe não encontrada: " + className);
        }

        Integer conModifier = getModifiers(character.getStatus().getConstitution());
        character.getStatus().setMaxHp(baseHp + conModifier);
        character.getStatus().setCurrentHp(character.getStatus().getMaxHp());
    }

    private void setCursedEnergyOnCreation(CharacterEntity character) {
        CharacterClass characterClass = character.getCharacterMulticlass().get(0).getCharacterClass();
        if (characterClass == null) {
            throw new IllegalArgumentException("Character class cannot be null");
        }

        if (Objects.equals(characterClass.getName(), "Lutador")) {
            character.getStatus().setMaxCursedEnergy(4);
        } else if (Objects.equals(characterClass.getName(), "Especialista em Combate")) {
            character.getStatus().setMaxCursedEnergy(4);
        } else if (Objects.equals(characterClass.getName(), "Especialista em Técnica")) {
            character.getStatus().setMaxCursedEnergy(6 + getModifiers(character.getStatus().getIntelligence()));
        } else if (Objects.equals(characterClass.getName(), "Controlador")) {
            character.getStatus().setMaxCursedEnergy(5 + getModifiers(character.getStatus().getWisdom()));
        } else if (Objects.equals(characterClass.getName(), "Suporte")) {
            character.getStatus().setMaxCursedEnergy(5 + getModifiers(character.getStatus().getCharisma()));
        } else if (Objects.equals(characterClass.getName(), "Restringido")) {
            character.getStatus().setMaxCursedEnergy(4); // Stamina Points for Restringido
        } else {
            throw new IllegalArgumentException("Classe não Encontrada: " + characterClass.getName());
        }
        character.getStatus().setCurrentCursedEnergy(character.getStatus().getMaxCursedEnergy());

    }

    private void setSoulPointsOnCreation(CharacterEntity character) {
        character.getStatus().setMaxSoulPoint(character.getStatus().getMaxHp());
        character.getStatus().setCurrentSoulPoint(character.getStatus().getMaxHp());
    }

    private void setHpSoulCursedEnergyOnCreation(CharacterEntity character) {
        setHpOnCreation(character);
        setCursedEnergyOnCreation(character);
        setSoulPointsOnCreation(character);
    }

    private void updateSoulPoints(CharacterStatus status) {
        if (Objects.equals(status.getMaxSoulPoint(), status.getCurrentSoulPoint())) {
            status.setMaxSoulPoint(status.getMaxHp());
            status.setCurrentSoulPoint(status.getMaxSoulPoint());
        } else {
            status.setMaxSoulPoint(status.getMaxHp());
        }
    }
}
