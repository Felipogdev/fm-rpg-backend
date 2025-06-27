package com.fmrpg.fmbackend.entities;

import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "uniforms")
@Getter
@Setter
public class Uniform extends ItemAbstract{

    @Column(name = "bonus_armor")
    Integer bonusArmor;

    @Column(name = "penalty")
    Integer penalty;

    public Uniform(Integer bonusArmor, Integer penalty, String name, Integer weight, Integer cost, String description) {
        this.bonusArmor = bonusArmor;
        this.penalty = penalty;
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.description = description;
        this.itemCategory = ItemCategory.UNIFORME;
    }

    public Uniform() {

    }
}
