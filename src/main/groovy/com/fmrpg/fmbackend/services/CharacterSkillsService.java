package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.entities.CharacterSkill;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.repositories.CharacterSkillsRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterSkillsService {

    private final CharacterSkillsRepository characterSkillsRepository;

    public CharacterSkillsService(
            CharacterSkillsRepository characterSkillsRepository

    ) {
        this.characterSkillsRepository = characterSkillsRepository;

    }

public void createSkill (CharacterStatus status) {
    CharacterSkill skill = new CharacterSkill();
    status.setSkills(skill);
    skill.setStatus(status);
    characterSkillsRepository.save(skill);
    }
}
