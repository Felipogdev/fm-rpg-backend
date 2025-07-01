package com.fmrpg.fmbackend.entities;


import com.fmrpg.fmbackend.enums.DamageType;
import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weapons")
@Getter
@Setter
@NoArgsConstructor
public class Weapon extends ItemAbstract {

    @ElementCollection
    private List<Integer> diceQuantity;

    @ElementCollection
    private List<Integer> diceValue;

    private Integer critMargin;

    @Enumerated(EnumType.STRING)
    private DamageType damageType;

    @ManyToOne
    @JoinColumn(name = "weapon_group_id", referencedColumnName = "id", nullable = false)
    private WeaponGroup weaponGroup;

    @ManyToMany
    @JoinTable(name = "weapon_weapon_properties",
            joinColumns = @JoinColumn(name = "weapon_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<WeaponProperties> weaponProperties = new ArrayList<>();

    public Weapon(String name, Integer weight, Integer cost, String description, List<Integer> diceQuantity, List<Integer> diceValue, Integer critMargin, DamageType damageType, WeaponGroup weaponGroup, List<WeaponProperties> weaponProperties ) {
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
       this.weaponProperties = weaponProperties;
    }
}
