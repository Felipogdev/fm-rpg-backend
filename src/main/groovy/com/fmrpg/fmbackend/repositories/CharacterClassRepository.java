package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
}
