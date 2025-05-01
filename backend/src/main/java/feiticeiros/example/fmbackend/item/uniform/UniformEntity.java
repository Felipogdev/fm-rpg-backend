package feiticeiros.example.fmbackend.item.uniform;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "uniforms")
public class UniformEntity extends ItemAbstractEntity {

    private Integer bonus_armor;

    private Integer penalty;

    //TODO: Os uniformes podem ser modificados, sendo possível adicionar uma modificação, botar isso no CharacterItem

}
