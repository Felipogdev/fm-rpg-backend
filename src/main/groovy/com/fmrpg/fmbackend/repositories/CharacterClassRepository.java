package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
    Optional<CharacterClass> findByName(String name);
}
