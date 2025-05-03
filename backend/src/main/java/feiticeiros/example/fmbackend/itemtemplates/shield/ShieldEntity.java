package feiticeiros.example.fmbackend.itemtemplates.shield;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shields")
@Getter
@Setter
public class ShieldEntity extends ItemTemplateEntity {

    private Integer bonus_armor;

    private Integer penalty;
}
