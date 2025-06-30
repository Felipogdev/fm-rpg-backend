package com.fmrpg.fmbackend.entities.characteritempkg;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.enums.SpecialItemCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "special_item_category")
@Entity
@NoArgsConstructor
public class SpecialItemCharacter extends  CharacterItem{

    SpecialItemCategory specialItemCategory;

    public SpecialItemCharacter(String name, String description, Integer weight, Integer cost, SpecialItemCategory specialItemCategory, CharacterEntity character){
        this.setName(name);
        this.setDescription(description);
        this.weight = weight;
        this.cost = cost;
        this.itemCategory = itemCategory.ESPECIAL;
        this.specialItemCategory = specialItemCategory;
        this.setCharacter(character);
    }
}
