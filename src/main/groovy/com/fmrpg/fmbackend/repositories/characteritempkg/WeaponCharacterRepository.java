package com.fmrpg.fmbackend.repositories.characteritempkg;

import com.fmrpg.fmbackend.entities.characteritempkg.WeaponCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponCharacterRepository extends JpaRepository<WeaponCharacter, Long> {
}
