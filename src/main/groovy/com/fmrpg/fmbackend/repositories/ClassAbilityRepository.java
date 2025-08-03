package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.characterpkg.ClassAbility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassAbilityRepository extends JpaRepository<ClassAbility,Long> {
    ClassAbility findByName(String name);
}
