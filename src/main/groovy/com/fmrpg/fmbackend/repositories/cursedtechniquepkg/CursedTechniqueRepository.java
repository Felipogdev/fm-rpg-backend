package com.fmrpg.fmbackend.repositories.cursedtechniquepkg;

import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursedTechniqueRepository extends JpaRepository<CursedTechnique, Long> {
}
