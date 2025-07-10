package com.fmrpg.fmbackend.entities.characterpkg;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "class_allowed_abilities",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private List<ClassAbility> availableAbilities = new ArrayList<>();

    public CharacterClass(String name, String description){
        this.name = name;
        this.description = description;
    }
}