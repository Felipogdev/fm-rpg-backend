package com.fmrpg.fmbackend.entities.characterpkg;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character_origins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OriginPerks> perks = new ArrayList<>();

    public CharacterOrigin(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
