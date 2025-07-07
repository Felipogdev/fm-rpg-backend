package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterStatus;
import com.fmrpg.fmbackend.mappers.CharacterStatusMapper;
import com.fmrpg.fmbackend.repositories.CharacterStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class CharacterStatusService {

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

    public void createCharacterStatus(CharacterEntity character, int[] statusArray) {
        CharacterStatus status = new CharacterStatus();
        status.setStrength(statusArray[0]);
        status.setConstitution(statusArray[1]);
        status.setIntelligence(statusArray[2]);
        status.setDexterity(statusArray[3]);
        status.setWisdom(statusArray[4]);
        status.setCharisma(statusArray[5]);

        character.setStatus(status);
        status.setCharacter(character);

        characterStatusRepository.save(status);

        characterSkillsService.createSkillsForCharacter(status);
    }

    private Integer getModifiers(Integer value) {
        if (value == null) {
            return 0;
        }
        return (int) Math.floor((value - 10) / 2.0);
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
        }

        updateIfNotNull(dto.currentHp(), status::setCurrentHp);

        if (dto.maxCursedEnergy() != null) {
            if (    Objects.equals(status.getMaxCursedEnergy(), status.getCurrentCursedEnergy())) {
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
        updateIfNotNull(dto.soulPoint(), status::setSoulPoint);

        characterStatusRepository.save(status);

        return characterStatusMapper.toDto(status);
    }
}
