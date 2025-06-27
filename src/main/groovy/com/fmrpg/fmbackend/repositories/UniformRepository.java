package com.fmrpg.fmbackend.repositories;

import com.fmrpg.fmbackend.entities.Uniform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniformRepository extends JpaRepository<Uniform, Long> {

}
