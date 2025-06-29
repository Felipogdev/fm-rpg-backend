package com.fmrpg.fmbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "character_item")
@Getter
@Setter
public class CharacterItem extends ItemAbstract {

    @Id
    Long id;

    private String name;


}
