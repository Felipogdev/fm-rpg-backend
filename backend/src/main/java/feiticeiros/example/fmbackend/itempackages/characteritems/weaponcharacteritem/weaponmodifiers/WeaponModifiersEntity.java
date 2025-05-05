package feiticeiros.example.fmbackend.itempackages.characteritems.weaponcharacteritem.weaponmodifiers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "weapon_modifiers")
@Getter
@Setter
public class WeaponModifiersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modifier_name;

    @Column(columnDefinition = "TEXT")
    private String modifier_description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<WeaponModifiersEntity> pre_requisites;
}
