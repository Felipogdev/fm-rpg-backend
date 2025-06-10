package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.CharacterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterStatusRepository extends JpaRepository<CharacterStatus, UUID> {
}
