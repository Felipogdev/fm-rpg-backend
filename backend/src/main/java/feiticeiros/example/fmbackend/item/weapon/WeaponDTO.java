package feiticeiros.example.fmbackend.item.weapon;

import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.item.ItemType;

import java.util.List;

public record WeaponDTO(
        Long id,
        String name,
        String description,
        Integer cost,
        ItemType type,
        Integer tier,
        Integer damageDiceQuantity,
        Integer damageDiceType,
        Integer criticalMargin,
        DamageType damageType,
        List<String> properties,
        String group
        ) { }
