package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.OAuth2UserDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.OAuthMapper;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OAuthMapper oAuthMapper;

    public UserService(UserRepository userRepository,
                       OAuthMapper oAuthMapper) {
        this.userRepository = userRepository;
        this.oAuthMapper = oAuthMapper;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email not found in OAuth2 user attributes");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("User with email " + email + " already exists. Skipping registration.");
            return;
        }

        OAuth2UserDto dto = new OAuth2UserDto(
                email,
                oauth2User.getAttribute("picture"),
                oauth2User.getAttribute("sub")
        );


        User user = oAuthMapper.toUser(dto);

        userRepository.save(user);
        System.out.println("New user registered: " + email);
    }

    public void addCharacterToUser(CharacterEntity character) {
        User user = character.getUser();
        user.getCharacters().add(character);
    }
}