package feiticeiros.example.fmbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        // Check if the user email is already in use
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        // Check if the username is already in use
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já está em uso");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
}
