package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    private String origin;

    private Integer level;

    private String character_class;

    private String image;

    //TODO: Adicionar atributo de campanha, inventário, status, descrição, técnica, rolls (talvez só fazer no front),
}
