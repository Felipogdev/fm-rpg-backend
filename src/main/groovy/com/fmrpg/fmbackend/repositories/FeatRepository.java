package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterFeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatRepository extends JpaRepository<CharacterFeat, Long> {

}
