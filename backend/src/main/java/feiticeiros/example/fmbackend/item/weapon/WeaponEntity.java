package feiticeiros.example.fmbackend.item.weapon;



import feiticeiros.example.fmbackend.DamageType;
import feiticeiros.example.fmbackend.item.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "weapons")
public class WeaponEntity extends ItemEntity {

    private Integer damageDiceQuantity;

    private Integer damageDiceType; //

    private Integer criticalMargin;

    private DamageType damageType;

    private List<String> properties;

    private String group;

}
