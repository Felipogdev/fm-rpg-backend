package com.fmrpg.fmbackend.entities.techniquepkg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "cursed_ability")
@NoArgsConstructor
public class CursedAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "character_cursed_technique_id")
    @JsonIgnore
    private CursedTechnique cursedTechnique;

    public CursedAbility(String name, String description ) {
        this.name = name;
        this.description = description;
    }
}
