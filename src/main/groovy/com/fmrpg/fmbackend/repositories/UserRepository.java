package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPrivateId(Long privateId);
    Optional<User> findByPublicId(UUID publicId);
    Optional<User> findByGoogleId(String googleId);
}
