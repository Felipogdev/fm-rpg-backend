package feiticeiros.example.fmbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // SELECT * FROM users WHERE email = ?;
    Optional<User> findByEmail(String email);
}

