package feiticeiros.example.fmbackend.item.uniform;

import feiticeiros.example.fmbackend.item.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "uniforms")
public class UniformEntity extends ItemEntity {

    private Integer armorBonus;

    private Integer penalty;

}
