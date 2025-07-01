package com.fmrpg.fmbackend.repositories.characteritempkg;

import com.fmrpg.fmbackend.entities.characteritempkg.ShieldCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShieldCharacterRepository extends JpaRepository<ShieldCharacter, Long> {
}
