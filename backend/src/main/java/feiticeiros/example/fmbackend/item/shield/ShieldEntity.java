package feiticeiros.example.fmbackend.item.shield;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shields")
@Getter
@Setter
public class ShieldEntity extends ItemAbstractEntity {

    private Integer bonus_armor;

    private Integer penalty;
}
