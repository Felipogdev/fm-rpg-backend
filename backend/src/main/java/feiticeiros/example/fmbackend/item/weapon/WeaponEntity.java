package feiticeiros.example.fmbackend.item.weapon;



import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "weapons")
@Getter
@Setter
public class WeaponEntity extends ItemAbstractEntity {

    private Integer damage_dice_quantity;

    private Integer damage_dice_type; // Pode ser d4,d6,d8, d10, d12, d16, d20

    private Integer critical_margin;

    @Enumerated(EnumType.STRING)
    private DamageType damage_type;

    @ElementCollection
    private List<String> properties;

    private String weapon_group;
}
