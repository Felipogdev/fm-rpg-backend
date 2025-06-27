package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.CharacterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterStatusRepository extends JpaRepository<CharacterStatus, Long> {

    Optional<CharacterStatus> findByCharacterPublicId(UUID publicId);
}
