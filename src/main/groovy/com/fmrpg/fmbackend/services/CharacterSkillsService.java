package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CharacterSkillDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.CharacterSkill;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import com.fmrpg.fmbackend.repositories.CharacterSkillsRepository;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

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

    private Integer calculateSkillValue(Integer skillMod, Integer level, Integer skillProficiency, Integer bonus){
        return skillMod+(level/2)+skillProficiency+bonus;
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public CharacterEntity updateSkill(CharacterSkillDto dto, CharacterEntity character) {

    CharacterSkill skill = character.getStatus().getSkills();

        updateIfNotNull(dto.acrobatics(), skill::setAcrobatics);
        updateIfNotNull(dto.cunning(), skill::setCunning);
        updateIfNotNull(dto.athletics(), skill::setAthletics);
        updateIfNotNull(dto.driving(), skill::setDriving);
        updateIfNotNull(dto.deception(), skill::setDeception);
        updateIfNotNull(dto.spellcasting(), skill::setSpellcasting);
        updateIfNotNull(dto.fortitude(), skill::setFortitude);
        updateIfNotNull(dto.stealth(), skill::setStealth);
        updateIfNotNull(dto.history(), skill::setHistory);
        updateIfNotNull(dto.integrity(), skill::setIntegrity);
        updateIfNotNull(dto.intimidation(), skill::setIntimidation);
        updateIfNotNull(dto.insight(), skill::setInsight);
        updateIfNotNull(dto.investigation(), skill::setInvestigation);
        updateIfNotNull(dto.fighting(), skill::setFighting);
        updateIfNotNull(dto.medicine(), skill::setMedicine);
        updateIfNotNull(dto.occultism(), skill::setOccultism);
        updateIfNotNull(dto.crafting(), skill::setCrafting);
        updateIfNotNull(dto.perception(), skill::setPerception);
        updateIfNotNull(dto.persuasion(), skill::setPersuasion);
        updateIfNotNull(dto.performance(), skill::setPerformance);
        updateIfNotNull(dto.marksmanship(), skill::setMarksmanship);
        updateIfNotNull(dto.sleightOfHand(), skill::setSleightOfHand);
        updateIfNotNull(dto.reflexes(), skill::setReflexes);
        updateIfNotNull(dto.religion(), skill::setReligion);
        updateIfNotNull(dto.survival(), skill::setSurvival);
        updateIfNotNull(dto.technology(), skill::setTechnology);
        updateIfNotNull(dto.willPower(), skill::setWillPower);

        characterSkillsRepository.save(skill);

        return character;
    }


}
