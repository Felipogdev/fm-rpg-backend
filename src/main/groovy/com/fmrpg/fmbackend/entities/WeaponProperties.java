package com.fmrpg.fmbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weapon_properties")
@AllArgsConstructor
@NoArgsConstructor
public class WeaponProperties {

    @Id
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
}
