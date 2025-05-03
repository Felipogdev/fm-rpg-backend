package feiticeiros.example.fmbackend.itemtemplates.uniform;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "uniforms")
public class UniformEntity extends ItemTemplateEntity {

    private Integer bonus_armor;

    private Integer penalty;
}
