package feiticeiros.example.fmbackend.user;


import feiticeiros.example.fmbackend.AbstractEntity;
import feiticeiros.example.fmbackend.characterpackages.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
public class User extends AbstractEntity {

    @NotBlank(message = "Email é obrigatório")
    @Email(message= "Email inválido")
    private String email;

    private String googleId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterEntity> characters = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    private Role role;

}
