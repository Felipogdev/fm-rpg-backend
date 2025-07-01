package com.fmrpg.fmbackend.repositories.characteritempkg;

import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterItemRepository extends JpaRepository<CharacterItem, Long> {
}
