package com.fmrpg.fmbackend.entities.techniquepkg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Column(name = "ability_cost")
    private Integer abilityCost;

    @ManyToOne
    @JoinColumn(name = "character_cursed_technique_id")
    @JsonIgnore
    private CursedTechnique cursedTechnique;

    @Min(1)
    @Max(5)
    private Integer tier;

    public CursedAbility(String name, String description ) {
        this.name = name;
        this.description = description;
        this.abilityCost = 0;
        this.tier = 1;
    }
}
