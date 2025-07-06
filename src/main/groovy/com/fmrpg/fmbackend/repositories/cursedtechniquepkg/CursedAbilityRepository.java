package com.fmrpg.fmbackend.repositories.cursedtechniquepkg;

import com.fmrpg.fmbackend.entities.techniquepkg.CursedAbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursedAbilityRepository extends JpaRepository<CursedAbility, Long> {
}
