package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.WeaponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeaponGroupRepository extends JpaRepository<WeaponGroup, Long> {
    Optional<WeaponGroup> findByName(String name);
}
