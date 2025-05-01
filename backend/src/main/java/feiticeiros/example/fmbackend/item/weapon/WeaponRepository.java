package feiticeiros.example.fmbackend.item.weapon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<WeaponEntity, Long> {
}
