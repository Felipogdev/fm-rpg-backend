package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.Shield;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShieldRepository extends JpaRepository<Shield, Long> {
}
