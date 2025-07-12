package com.fmrpg.fmbackend.entities.characterpkg;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="feats")
@Getter
@Setter
@NoArgsConstructor
public class CharacterFeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String requirement;

    @ManyToMany(mappedBy = "feats")
    private List<CharacterEntity> characters = new ArrayList<>();

    public CharacterFeat(String name, String description, String requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
    }

}
