package com.fmrpg.fmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "character_skills")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkill {

    @Id
    private UUID characterId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "character_id")
    @JsonIgnore
    private CharacterStatus status;

    private Integer cunning = 0;

    private Integer athletics = 0;

    private Integer driving = 0;

    private Integer deception = 0;

    private Integer spellcasting = 0;

    private Integer fortitude = 0;

    private Integer stealth = 0;

    private Integer history = 0;

    private Integer integrity = 0;

    private Integer intimidation = 0;

    private Integer insight = 0;

    private Integer investigation = 0;

    private Integer fighting = 0;

    private Integer medicine = 0;

    private Integer occultism = 0;

    private Integer crafting = 0;

    private Integer perception = 0;

    private Integer persuasion = 0;

    private Integer performance = 0;

    private Integer marksmanship = 0;

    private Integer sleightOfHand = 0;

    private Integer reflexes = 0;

    private Integer religion = 0;

    private Integer survival = 0;

    private Integer technology = 0;

    private Integer willPower = 0;

}
