package feiticeiros.example.fmbackend.item.special;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialItemRepository extends JpaRepository<SpecialItemEntity, Long> {
}
