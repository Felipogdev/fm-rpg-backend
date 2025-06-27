package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.CharacterOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterOriginRepository extends JpaRepository<CharacterOrigin, Long> {
}
