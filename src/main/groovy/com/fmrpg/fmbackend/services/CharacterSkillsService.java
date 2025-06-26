package com.fmrpg.fmbackend.services;


import com.fmrpg.fmbackend.dtos.characterdtos.CharacterSkillsDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterSkill;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.entities.Skills;
import com.fmrpg.fmbackend.enums.SkillProficiency;
import com.fmrpg.fmbackend.repositories.CharacterSkillsRepository;
import com.fmrpg.fmbackend.repositories.SkillsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterSkillsService {

    private final CharacterSkillsRepository characterSkillsRepository;
    private final SkillsRepository skillsRepository;

    public CharacterSkillsService(CharacterSkillsRepository characterSkillsRepository,
                                  SkillsRepository skillsRepository) {
        this.characterSkillsRepository = characterSkillsRepository;
        this.skillsRepository = skillsRepository;
    }

    public void createSkillsForCharacter(CharacterStatus status) {
        List<Skills> allSkills = skillsRepository.findAll();

        List<CharacterSkill> characterSkills = allSkills.stream()
                .map(skill -> {
                    CharacterSkill cs = new CharacterSkill();
                    cs.setStatus(status);
                    cs.setSkill(skill);
                    cs.setProficiency(SkillProficiency.NONE);
                    return cs;
                })
                .collect(Collectors.toList());

        status.getSkills().addAll(characterSkills);
        characterSkillsRepository.saveAll(characterSkills);
    }

    @Transactional
    public void updateCharacterSkill(CharacterEntity character, CharacterSkillsDto dto) {
        CharacterStatus status = character.getStatus();

        CharacterSkill characterSkill = characterSkillsRepository
                .findByStatusAndSkillId(status, dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Skill not found for character"));

        if (dto.proficiency() != null) {
            characterSkill.setProficiency(dto.proficiency());
        }

        if (dto.bonus() != null) {
            characterSkill.setBonus(dto.bonus());
        }

        characterSkillsRepository.save(characterSkill);
    }


}

