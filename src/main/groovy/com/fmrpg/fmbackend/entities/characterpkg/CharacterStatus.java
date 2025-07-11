package com.fmrpg.fmbackend.entities.characterpkg;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "character_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterStatus {

    @Id
    private Long characterPrivateId;

    @OneToOne
    @MapsId
    @JsonBackReference
    @JoinColumn(name = "character_private_id")
    private CharacterEntity character;

    @Column(name = "current_hp")
    private Integer currentHp = 4;

    @Column(name = "max_hp")
    private Integer maxHp = 10;

    @Column (name = "current_cursed_energy")
    private Integer currentCursedEnergy= 4;

    @Column (name = "max_cursed_energy")
    private Integer maxCursedEnergy=10;

    @Column (name = "con")
    private Integer constitution;

    @Column (name = "int")
    private Integer intelligence;

    @Column (name = "dex")
    private Integer dexterity;

    @Column (name = "str")

    private Integer strength;

    @Column (name = "wis")
    private Integer wisdom;

    @Column (name = "cha")
    private Integer charisma;

    @Column (name = "initiative")
    private Integer initiative;

    @Column (name = "movement")
    private Integer movement;

    @Column (name = "armor_class")
    private Integer defense;

    @Column (name = "max_soul_point")
    private Integer maxSoulPoint;

    @Column( name = "current_soul_point")
    private Integer currentSoulPoint;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterSkill> skills = new ArrayList<>();
}
