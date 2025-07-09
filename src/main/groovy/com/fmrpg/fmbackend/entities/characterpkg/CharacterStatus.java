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
    private Integer currentHp;

    @Column(name = "max_hp")
    private Integer maxHp;

    @Column (name = "current_cursed_energy")
    private Integer currentCursedEnergy;

    @Column (name = "max_cursed_energy")
    private Integer maxCursedEnergy;

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

    public CharacterStatus(CharacterEntity character, Integer strength, Integer constitution, Integer intelligence, Integer dexterity, Integer wisdom, Integer charisma) {
        this.character = character;
        this.strength = strength != null ? strength : 8;
        this.constitution = constitution != null ? constitution : 8;
        this.intelligence = intelligence != null ? intelligence : 8;
        this.dexterity = dexterity != null ? dexterity : 8;
        this.wisdom = wisdom != null ? wisdom : 8;
        this.charisma = charisma != null ? charisma : 8;
    }
}
