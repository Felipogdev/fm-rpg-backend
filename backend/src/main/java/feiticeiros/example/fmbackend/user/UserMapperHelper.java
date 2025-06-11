package feiticeiros.example.fmbackend.user;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserMapperHelper {

    private final UserRepository userRepository;

    public UserMapperHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Named("mapUserIdToUser")
    public User mapUserIdToUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

