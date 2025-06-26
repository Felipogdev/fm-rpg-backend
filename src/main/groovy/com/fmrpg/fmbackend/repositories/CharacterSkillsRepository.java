package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.CharacterSkill;
import com.fmrpg.fmbackend.entities.CharacterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterSkillsRepository extends JpaRepository<CharacterSkill, UUID> {
    Optional<CharacterSkill> findByStatusAndSkillId(CharacterStatus status, Long skillId);
}
