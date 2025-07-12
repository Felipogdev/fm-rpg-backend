package com.fmrpg.fmbackend.entities.characterpkg;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "origin_perks")
@Getter
@Setter
@NoArgsConstructor
public class OriginPerks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private CharacterOrigin origin;




    

    public OriginPerks(String name, String description, CharacterOrigin origin) {
        this.name = name;
        this.description = description;
        this.origin = origin;
    }
}
