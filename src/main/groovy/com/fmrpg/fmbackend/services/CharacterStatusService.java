package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterStatusResponseDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.mappers.CharacterResponseMapper;
import com.fmrpg.fmbackend.mappers.CharacterStatusMapper;
import com.fmrpg.fmbackend.repositories.CharacterStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        characterSkillsService.createSkill(status);
    }

    private Integer getModifiers(Integer value) {
        if (value == null) {
            return 0;
        }
        return (int) Math.floor((value - 10) / 2.0);
    }

    public CharacterStatusResponseDto updateCharacterStatus(UUID characterPublicId, CharacterStatusDto dto) {

        CharacterStatus status = characterStatusRepository.findByCharacter_PublicId(characterPublicId)
                .orElseThrow(() -> new EntityNotFoundException("CharacterStatus not found for character with id " + characterPublicId));


        if(dto.maxHp() != null) {
            status.setMaxHp(dto.maxHp());
        }
        if(dto.currentHp() != null) {
            status.setCurrentHp(dto.currentHp());
        }
        if(dto.maxCursedEnergy() != null) {
            status.setMaxCursedEnergy(dto.maxCursedEnergy());
        }
        if(dto.currentCursedEnergy() != null) {
            status.setCurrentCursedEnergy(dto.currentCursedEnergy());
        }
        if(dto.constitution() != null) {
            status.setConstitution(dto.constitution());
        }
        if(dto.intelligence() != null) {
            status.setIntelligence(dto.intelligence());
        }
        if(dto.dexterity() != null) {
            status.setDexterity(dto.dexterity());
        }
        if(dto.strength() != null) {
            status.setStrength(dto.strength());
        }
        if(dto.wisdom() != null) {
            status.setWisdom(dto.wisdom());
        }
        if(dto.charisma() != null) {
            status.setCharisma(dto.charisma());
        }
        if(dto.initiative() != null) {
            status.setInitiative(dto.initiative());
        }
        if(dto.movement() != null) {
            status.setMovement(dto.movement());
        }
        if(dto.armorClass() != null) {
            status.setArmorClass(dto.armorClass());
        }
        if(dto.soulPoint() != null) {
            status.setSoulPoint(dto.soulPoint());
        }

       characterStatusRepository.save(status);

        return characterStatusMapper.toDto(status);
    }
}
