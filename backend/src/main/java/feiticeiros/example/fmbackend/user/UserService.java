package feiticeiros.example.fmbackend.user;

import feiticeiros.example.fmbackend.characterpackages.character.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
            private UserRepository userRepository;

    @Autowired
            private PasswordEncoder passwordEncoder;

    @Autowired
            private UserMapper userMapper;

    CharacterEntity characterEntity = new CharacterEntity();

    public void addCharacter(CharacterEntity characterEntity) {
        User user = characterEntity.getUser();
        user.getCharacters().add(characterEntity);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado com o id: " + id));
    }


    public User registerUser(UserDTO userDTO) {
        // Check if the user email is already in use
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
