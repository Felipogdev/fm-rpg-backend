package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.ItemAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemAbstract, Long> {
    ItemAbstract findByName(String name);
}
