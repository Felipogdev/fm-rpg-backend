package feiticeiros.example.fmbackend.characteritems.weaponcharacteritem.weaponmodifiers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponModifiersRepository extends JpaRepository<WeaponModifiersEntity, Long> {
}
