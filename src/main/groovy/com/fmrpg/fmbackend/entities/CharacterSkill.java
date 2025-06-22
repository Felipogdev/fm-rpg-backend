package com.fmrpg.fmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "character_skills")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkill {

    @Id
    private Long privateId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "character_id")
    @JsonIgnore
    private CharacterStatus status;

    private int acrobatics = 0;

    private int cunning = 0;

    private int athletics = 0;

    private int driving = 0;

    private int deception = 0;

    private int spellcasting = 0;

    private int fortitude = 0;

    private int stealth = 0;

    private int history = 0;

    private int integrity = 0;

    private int intimidation = 0;

    private int insight = 0;

    private int investigation = 0;

    private int fighting = 0;

    private int medicine = 0;

    private int occultism = 0;

    private int crafting = 0;

    private int perception = 0;

    private int persuasion = 0;

    private int performance = 0;

    private int marksmanship = 0;

    private int sleightOfHand = 0;

    private int reflexes = 0;

    private int religion = 0;

    private int survival = 0;

    private int technology = 0;

    private int willPower = 0;
}
