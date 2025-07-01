package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.WeaponGroup;
import com.fmrpg.fmbackend.entities.WeaponProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeaponPropertiesRepository extends JpaRepository<WeaponProperties, Long> {
    Optional<WeaponProperties> findByName(String name);
}
