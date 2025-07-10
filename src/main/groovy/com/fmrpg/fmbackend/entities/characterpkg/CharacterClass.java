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

    @OneToMany(mappedBy = "allowedClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassAbility> abilities = new ArrayList<>();

    public CharacterClass(String name, String description){
        this.name = name;
        this.description = description;
    }
}