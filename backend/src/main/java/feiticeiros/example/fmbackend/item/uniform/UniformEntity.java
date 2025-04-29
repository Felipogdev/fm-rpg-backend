package feiticeiros.example.fmbackend.item.uniform;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "uniforms")
public class UniformEntity extends ItemAbstractEntity {

    private Integer armorBonus;

    private Integer penalty;

}
