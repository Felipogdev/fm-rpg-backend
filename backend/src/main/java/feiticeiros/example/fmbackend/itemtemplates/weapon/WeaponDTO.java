package feiticeiros.example.fmbackend.itemtemplates.weapon;

import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.itemtemplates.ItemType;

import java.util.List;

public record WeaponDTO(
        Long id,
        String name,
        String description,
        Integer cost,
        ItemType type,
        Integer tier,
        Integer inventory_space,
        Integer damage_dice_quantity,
        Integer damage_dice_type,
        Integer critical_margin,
        DamageType damage_type,
        List<String> properties,
        String weapon_group,
        Integer two_handed_damage_dice_quantity,
        Integer two_handed_damage_dice_type
        ) { }
