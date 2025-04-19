package feiticeiros.example.fmbackend.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String image;

}
