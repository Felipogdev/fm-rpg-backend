package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    boolean existsByName(String name);
}
