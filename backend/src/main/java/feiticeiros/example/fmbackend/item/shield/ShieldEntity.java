package feiticeiros.example.fmbackend.item.shield;

import feiticeiros.example.fmbackend.item.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "shields")
public class ShieldEntity extends ItemEntity {

    private Integer bonusArmor;

    private Integer penalty;

    private Integer cost;
}
