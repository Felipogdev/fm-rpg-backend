package feiticeiros.example.fmbackend.user;


import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, String password, String image) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getImage());
    }

}
