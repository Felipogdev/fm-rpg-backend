package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CursedAbilityDto;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedAbility;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;
import com.fmrpg.fmbackend.repositories.UserRepository;
import com.fmrpg.fmbackend.repositories.cursedtechniquepkg.CursedAbilityRepository;
import com.fmrpg.fmbackend.repositories.cursedtechniquepkg.CursedTechniqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursedAbilityService {

    private final CursedTechniqueRepository cursedTechniqueRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterService characterService;
    private final CursedTechniqueService cursedTechniqueService;
    private final CursedAbilityRepository cursedAbilityRepository;


    public CursedAbilityService(CursedTechniqueRepository cursedTechniqueRepository, UserRepository userRepository, UserService userService, CharacterService characterService, CursedTechniqueService cursedTechniqueService, CursedAbilityRepository cursedAbilityRepository) {
        this.cursedTechniqueRepository = cursedTechniqueRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.characterService = characterService;
        this.cursedTechniqueService = cursedTechniqueService;
        this.cursedAbilityRepository = cursedAbilityRepository;
    }

    private void validateCharacterFromUser(OAuth2User oAuth2User, CharacterEntity character) {
        User user = userRepository.findByGoogleId(oAuth2User.getName()).orElseThrow();

        if(!userService.isCharacterFromuser(user, character)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private void validateAbilityFromTechnique(CursedTechnique technique, CursedAbility ability) {
        if (!cursedTechniqueService.isAbilityFromTechnique(technique, ability)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public CursedAbility cursedTechnique(OAuth2User oAuth2User, CharacterEntity character, CursedTechnique technique) {
        validateCharacterFromUser(oAuth2User, character);


        List<CursedAbility> abilityList = technique.getAbilities();

        CursedAbility newAbility = new CursedAbility("Nome da Abilidade", "Descrição da Abilidade");

        abilityList.add(newAbility);
        cursedTechniqueRepository.save(technique);

        return newAbility;
    }

    public CursedAbility updateAbility(OAuth2User oAuth2User, CharacterEntity character, CursedAbilityDto dto, Long abilityId) {

        validateCharacterFromUser(oAuth2User, character);
        CursedAbility ability = cursedAbilityRepository.findById(abilityId).orElseThrow(()-> new RuntimeException("Ability não encontrada"));
        validateAbilityFromTechnique(character.getTechnique(), ability);

        if (dto.name() != null) {
            ability.setName(dto.name());
        }

        if (dto.description() != null) {
            ability.setDescription(dto.description());
        }

        cursedAbilityRepository.save(ability);
        return ability;
    }



}
