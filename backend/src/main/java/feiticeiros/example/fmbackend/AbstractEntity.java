package feiticeiros.example.fmbackend;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    //private Date createdAt;

}
