package feiticeiros.example.fmbackend.itempackages.itemtemplates.shield;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShieldRepository extends JpaRepository<ShieldEntity, Long> {
}
