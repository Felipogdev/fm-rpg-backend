package feiticeiros.example.fmbackend.user;

import feiticeiros.example.fmbackend.character.CharacterDTO;
import feiticeiros.example.fmbackend.character.CharacterEntity;
import feiticeiros.example.fmbackend.character.CharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private UserMapper userMapper;


    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public List<CharacterDTO> getCharacters(@PathVariable UUID id) {
        return userService.getUserById(id)
                .getCharacters()
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.registerUser(userDTO);
         return userMapper.toDTO(savedUser);
    }
}

