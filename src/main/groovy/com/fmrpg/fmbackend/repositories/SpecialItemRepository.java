package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.SpecialItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialItemRepository extends JpaRepository<SpecialItems, Long> {
}
