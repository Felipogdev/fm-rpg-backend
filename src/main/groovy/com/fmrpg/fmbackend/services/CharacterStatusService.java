package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CharacterStatusDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.repositories.CharacterStatusRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CharacterStatusService {

    private CharacterStatusRepository characterStatusRepository;

    public CharacterStatusService(CharacterStatusRepository characterStatusRepository) {
        this.characterStatusRepository = characterStatusRepository;
    }

    public void crateCharacterStatus(CharacterEntity character) {
        CharacterStatus status = new CharacterStatus();

        character.setStatus(status);
        status.setCharacter(character);

        characterStatusRepository.save(status);
    }

    private Integer getModifiers(Integer value) {
        if (value == null) {
            return 0;
        }
        return (int) Math.floor((value - 10) / 2.0);
    }

    public CharacterStatus updateCharacterStatus(UUID id, CharacterStatusDto dto) {
        if (dto == null || id == null) {
            throw new IllegalArgumentException("CharacterStatusDto and ID must not be null");
        }

        CharacterStatus status = characterStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character status not found"));

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

        return characterStatusRepository.save(status);
    }
}
