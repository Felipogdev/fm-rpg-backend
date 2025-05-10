package feiticeiros.example.fmbackend.user;

import feiticeiros.example.fmbackend.character.CharacterDTO;
import feiticeiros.example.fmbackend.character.CharacterEntity;
import feiticeiros.example.fmbackend.character.CharacterMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final CharacterMapper characterMapper;

    public UserController(UserService userService, UserMapper userMapper, CharacterMapper characterMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.characterMapper = characterMapper;
    }
    
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

