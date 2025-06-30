package com.fmrpg.fmbackend.entities.characteritempkg;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "uniform_character")
@NoArgsConstructor
public class UniformCharacter extends CharacterItem {

    @Column(name = "bonus_armor")
    Integer bonusArmor;

    @Column(name = "penalty")
    Integer penalty;

    public UniformCharacter(String name,String description ,Integer penalty, Integer bonusArmor, Integer weight, Integer cost, CharacterEntity character) {
        this.setName(name);
        this.setDescription(description);
        this.bonusArmor = bonusArmor;
        this.penalty = penalty;
        this.weight = weight;
        this.cost = cost;
        this.itemCategory = ItemCategory.UNIFORME;
        this.setCharacter(character);
    }

}
