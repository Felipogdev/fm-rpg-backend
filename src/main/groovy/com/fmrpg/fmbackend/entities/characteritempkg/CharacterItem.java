package com.fmrpg.fmbackend.entities.characteritempkg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmrpg.fmbackend.entities.ItemAbstract;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "character_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class CharacterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private CharacterEntity character;

    @Enumerated(EnumType.STRING)
    ItemCategory itemCategory;

    @Column(name = "weight")
    Integer weight;

    @Column(name = "cost")
    Integer cost;

    public CharacterItem(String name, String description, CharacterEntity character, ItemCategory itemCategory, Integer weight, Integer cost) {
        this.name = name;
        this.description = description;
        this.character = character;
        this.itemCategory = itemCategory;
        this.weight = weight;
        this.cost = cost;
    }

}
