package feiticeiros.example.fmbackend.user;


import feiticeiros.example.fmbackend.AbstractEntity;
import feiticeiros.example.fmbackend.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Table(name = "users")
public class User extends AbstractEntity {

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(min = 2, max = 100, message = "Nome de usuário deve ter entre 2 e 100 caracteres")
    private String username;

    @NotBlank(message = "Email é obrigatório")
    @Email(message= "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;

    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterEntity> characters = new ArrayList<>();

}
