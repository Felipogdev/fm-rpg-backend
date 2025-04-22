package feiticeiros.example.fmbackend.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.registerUser(userDTO);
        return userMapper.toDTO(savedUser);
    }
}

