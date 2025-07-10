package com.fmrpg.fmbackend.entities.characterpkg;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_abilities")
@Getter
@NoArgsConstructor
@Setter
public class ClassAbility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ability_level")
    private Integer abilityLevel;

    @ManyToOne
    @JoinColumn(name = "allowed_class_id")
    private CharacterClass allowedClass;

    private String requirement;

    public ClassAbility(String name, String description, Integer abilityLevel, CharacterClass allowedClass, String requirement) {
        this.name = name;
        this.description = description;
        this.abilityLevel = abilityLevel;
        this.allowedClass = allowedClass;
        this.requirement = requirement;
    }

    public ClassAbility(String name, String description, Integer abilityLevel, CharacterClass allowedClass) {
        this.name = name;
        this.description = description;
        this.abilityLevel = abilityLevel;
        this.allowedClass = allowedClass;
    }
}
