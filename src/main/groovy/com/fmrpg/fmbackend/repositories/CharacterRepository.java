package com.fmrpg.fmbackend.repositories;


import com.fmrpg.fmbackend.entities.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository <CharacterEntity, UUID> {
}
