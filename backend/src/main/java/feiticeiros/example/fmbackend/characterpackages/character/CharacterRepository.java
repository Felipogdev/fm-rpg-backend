package feiticeiros.example.fmbackend.characterpackages.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {
}
