package feiticeiros.example.fmbackend.itempackages.characteritems.weaponcharacteritem;

import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.itempackages.characteritems.characteritem.CharacterItemEntity;
import feiticeiros.example.fmbackend.itempackages.characteritems.weaponcharacteritem.weaponmodifiers.WeaponModifiersEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weapon_character")
@Getter
@Setter
public class WeaponCharacter extends CharacterItemEntity {

    private Integer custom_damage_dice_quantity;

    private Integer custom_damage_dice_type;

    private Integer custom_two_handed_damage_dice_quantity;

    private Integer custom_two_handed_damage_dice_type;

    private Integer custom_critical_margin;

    @Enumerated(EnumType.STRING)
    private DamageType custom_damage_type;

    @ElementCollection
    private List<String> properties;

    @ManyToMany
    private List <WeaponModifiersEntity> modifiers = new ArrayList<>();

    private String custom_weapon_group;
}
