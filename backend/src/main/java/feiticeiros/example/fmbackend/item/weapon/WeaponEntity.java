package feiticeiros.example.fmbackend.item.weapon;



import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "weapons")
public class WeaponEntity extends ItemAbstractEntity {

    private Integer damageDiceQuantity;

    private Integer damageDiceType; // Pode ser d4,d6,d8, etc...

    private Integer criticalMargin;

    private DamageType damageType;

    private List<String> properties;

    private String group;
    

}
