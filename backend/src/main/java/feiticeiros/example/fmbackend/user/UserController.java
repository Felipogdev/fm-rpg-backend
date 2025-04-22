package feiticeiros.example.fmbackend.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.registerUser(userDTO);
        return UserMapper.toDTO(savedUser);
    }
}

