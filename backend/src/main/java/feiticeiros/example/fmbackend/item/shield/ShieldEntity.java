package feiticeiros.example.fmbackend.item.shield;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "shields")
public class ShieldEntity extends ItemAbstractEntity {

    private Integer bonusArmor;

    private Integer penalty;
}
