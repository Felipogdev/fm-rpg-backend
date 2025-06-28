package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.OAuth2UserDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.mappers.OAuthMapper;
import com.fmrpg.fmbackend.repositories.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

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
    }

    public void addCharacterToUser(CharacterEntity character) {
        User user = character.getUser();
        user.getCharacters().add(character);
    }

    public User findUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId).orElse(null);
    }

    public User validateUser(OAuth2User oauth2User) {
        if (oauth2User == null) {
            throw new RuntimeException("User not Authenticated");
        }

        String googleId = oauth2User.getName();
        User user = findUserByGoogleId(googleId);

        if (user == null) {
            String email = oauth2User.getAttribute("email");
            throw new RuntimeException("Usuário com OAuth ID " + googleId + " (email: " + email + ") não encontrado.");
        }

        return user;
    }
}