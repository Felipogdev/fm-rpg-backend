package com.fmrpg.fmbackend.entities.characterpkg;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "character_multiclass")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMulticlass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "private_id", nullable = false)
    @JsonBackReference
    private CharacterEntity character;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private CharacterClass characterClass;

    @Column(name = "level", nullable = false)
    private Integer level;

    public CharacterMulticlass(CharacterEntity character, CharacterClass characterClass) {
        this.character = character;
        this.characterClass = characterClass;
        this.level = 1;
    }

}
