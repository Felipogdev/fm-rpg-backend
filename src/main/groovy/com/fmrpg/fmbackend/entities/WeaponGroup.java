package com.fmrpg.fmbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weapon_group")
@Getter
@Setter
@NoArgsConstructor
public class WeaponGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    public WeaponGroup(String name) {
        this.name = name;
    }

}
