package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterFeat;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.repositories.FeatRepository;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class FeatService {

    private final FeatRepository featRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;

    public FeatService(FeatRepository featRepository, UserService userService, UserRepository userRepository, CharacterRepository characterRepository) {
        this.featRepository = featRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.characterRepository = characterRepository;
    }

    public CharacterFeat giveCharacterFeat(OAuth2User oAuth2User, CharacterEntity character, Long FeatId) {

        User user = userRepository.findByGoogleId(oAuth2User.getAttribute("sub")).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")
        );

        if(!userService.isCharacterFromuser(user, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        };

        List<CharacterFeat> characterFeats = character.getFeats();
        CharacterFeat feat = featRepository.findById(FeatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feat not found"));

        if(characterFeats.contains(feat)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character already has this feat");
        }

        characterFeats.add(feat);
        characterRepository.save(character);

        return feat;
    }

    public List<CharacterFeat> getCharacterFeats(OAuth2User oAuth2User, CharacterEntity character) {
        User user = userRepository.findByGoogleId(oAuth2User.getAttribute("sub")).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")
        );

        if(!userService.isCharacterFromuser(user, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return character.getFeats();
    }

    public void removeCharacterFeat(OAuth2User oAuth2User, CharacterEntity character, Long featId) {
        User user = userRepository.findByGoogleId(oAuth2User.getAttribute("sub")).orElseThrow();

        if(!userService.isCharacterFromuser(user, character)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        CharacterFeat feat = featRepository.findById(featId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feat not found"));

        if(!character.getFeats().remove(feat)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character does not have this feat");
        }

        characterRepository.save(character);
    }

    public List<CharacterFeat> getAllFeats() {
        return featRepository.findAll();
    }

}
