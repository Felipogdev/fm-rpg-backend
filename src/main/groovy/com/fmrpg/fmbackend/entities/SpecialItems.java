package com.fmrpg.fmbackend.entities;

import com.fmrpg.fmbackend.enums.SpecialItemCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "special_items")
@Getter
@Setter
@NoArgsConstructor
public class SpecialItems extends ItemAbstract {

    SpecialItemCategory specialItemCategory;

    public SpecialItems(String name, String description, Integer weight, Integer cost, SpecialItemCategory specialItemCategory){
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.cost = cost;
        this.itemCategory = itemCategory.ESPECIAL;
        this.specialItemCategory = specialItemCategory;
    }

}
