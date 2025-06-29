package com.fmrpg.fmbackend.entities;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
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
public class CharacterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemAbstract item;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;

}
