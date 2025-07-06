package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.CursedTechniqueDto;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedAbility;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;
import com.fmrpg.fmbackend.repositories.UserRepository;
import com.fmrpg.fmbackend.repositories.cursedtechniquepkg.CursedTechniqueRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.fmrpg.fmbackend.services.CharacterService;

@Service
public class CursedTechniqueService {

    private final CursedTechniqueRepository cursedTechniqueRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterService characterService;

    public CursedTechniqueService(CursedTechniqueRepository cursedTechniqueRepository, UserRepository userRepository, UserService userService, @Lazy CharacterService characterService) {
        this.cursedTechniqueRepository = cursedTechniqueRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.characterService = characterService;
    }

    private void validateCharacterFromUser(OAuth2User oAuth2User, CharacterEntity character) {
        User user = userRepository.findByGoogleId(oAuth2User.getName()).orElseThrow(() -> new RuntimeException("Usuário oauth2 não encontrado"));

        if(!userService.isCharacterFromuser(user, character)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private void validadeTechniqueFromCharacter(CharacterEntity character, CursedTechnique technique) {
        if (!characterService.isTechniqueFromCharacter(technique, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public void createTechnique(CharacterEntity character) {
        CursedTechnique technique = new CursedTechnique();
        character.setTechnique(technique);
        technique.setCharacter(character);

        cursedTechniqueRepository.save(technique);
    }

    public CursedTechnique updateTechnique(OAuth2User oAuth2User, CharacterEntity character, CursedTechniqueDto dto) {
        validateCharacterFromUser(oAuth2User, character);

    CursedTechnique technique = character.getTechnique();


       if (dto.name() != null) {
           technique.setName(dto.name());
       }

       if (dto.description() != null) {
           technique.setDescription(dto.description());
       }

       cursedTechniqueRepository.save(technique);

       return technique;
    }

    public boolean isAbilityFromTechnique(CursedTechnique technique, CursedAbility ability) {
        if (technique == null || ability == null) return false;
        return technique.getAbilities().contains(ability);
    }


}
