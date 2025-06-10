package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.repositories.CharacterStatusRepository;
import org.springframework.stereotype.Service;

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
}
