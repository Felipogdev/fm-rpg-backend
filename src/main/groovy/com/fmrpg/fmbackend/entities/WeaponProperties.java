package com.fmrpg.fmbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weapon_properties")
@NoArgsConstructor
@Getter
@Setter
public class WeaponProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    public WeaponProperties(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
