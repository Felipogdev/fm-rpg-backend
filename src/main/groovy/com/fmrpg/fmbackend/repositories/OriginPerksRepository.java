package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.characterpkg.OriginPerks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginPerksRepository extends JpaRepository<OriginPerks, Long> {
}
