package com.fmrpg.fmbackend.entities.techniquepkg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character_technique")
@Getter
@Setter
public class CursedTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "character_private_id")
    @JsonIgnore
    private CharacterEntity character;

    @OneToMany(mappedBy = "cursedTechnique", cascade = CascadeType.ALL)
    private List<CursedAbility> abilities = new ArrayList<>();

    public CursedTechnique() {
        this.name = "Nome Técnica Amaldiçoada";
        this.description = "Descriçâo Técnica Amaldiçoada";
    }
}
