package feiticeiros.example.fmbackend.characterstatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusRepository extends JpaRepository<StatusEntity, UUID> {
}
