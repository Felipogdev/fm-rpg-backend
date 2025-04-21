package feiticeiros.example.fmbackend.user;

public class UserMapper {
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setImage(dto.getImage());
        user.setUsername(dto.getUsername());
        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        //Dont return password
        dto.setImage(user.getImage());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
