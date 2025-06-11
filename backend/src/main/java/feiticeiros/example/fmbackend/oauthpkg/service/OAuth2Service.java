package feiticeiros.example.fmbackend.oauthpkg.service;

import feiticeiros.example.fmbackend.user.User;
import feiticeiros.example.fmbackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuth2Service {

    private final UserRepository userRepository;

    public OAuth2Service(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String email, String googleId, String image, String name) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setGoogleId(googleId);
            user.setImage(image);
            user.setName(name);
            userRepository.save(user);
        }
    }






}
