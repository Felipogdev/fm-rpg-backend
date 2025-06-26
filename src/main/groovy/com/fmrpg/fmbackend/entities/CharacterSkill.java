package com.fmrpg.fmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmrpg.fmbackend.enums.SkillProficiency;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CharacterStatus status;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skills skill;

    @Enumerated(EnumType.STRING)
    private SkillProficiency proficiency;

    @Column(name = "bonus")
    private int bonus = 0;
}
