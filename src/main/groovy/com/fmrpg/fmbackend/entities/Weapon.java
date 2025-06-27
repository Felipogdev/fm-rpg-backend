package com.fmrpg.fmbackend.entities;


import com.fmrpg.fmbackend.enums.DamageType;
import com.fmrpg.fmbackend.enums.ItemCategory;
import com.fmrpg.fmbackend.enums.WeaponGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "weapons")
@Getter
@Setter
public class Weapon extends ItemAbstract {

    private Integer diceQuantity;

    private Integer diceValue;

    private Integer critMargin;

    @Enumerated(EnumType.STRING)
    private DamageType damageType;

    @Enumerated(EnumType.STRING)
    private WeaponGroup weaponGroup;

//    List<WeaponProperties> weaponProperties;
//List<WeaponProperties> weaponProperties
    public Weapon(String name, Integer weight, Integer cost, String description, Integer diceQuantity, Integer diceValue, Integer critMargin, DamageType damageType, WeaponGroup weaponGroup ) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.description = description;
        this.itemCategory = ItemCategory.ARMA;
        this.diceQuantity = diceQuantity;
        this.diceValue = diceValue;
        this.critMargin = critMargin;
        this.damageType = damageType;
        this.weaponGroup = weaponGroup;
//        this.weaponProperties = weaponProperties;
    }

    public Weapon() {

    }
}
