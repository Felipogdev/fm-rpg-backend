package com.fmrpg.fmbackend.entities.characterpkg;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.entities.characteritempkg.CharacterItem;
import com.fmrpg.fmbackend.entities.techniquepkg.CursedTechnique;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "private_id", nullable = false, unique = true)
    private Long privateId;

    @Column(name = "public_id", updatable = false, nullable = false)
    private UUID publicId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "private_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column (name = "name")
    private String name;

    @Column(name = "level")
    private Integer level = 1;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private CharacterClass characterClass;

    @ManyToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "id", nullable = false)
    private CharacterOrigin characterOrigin;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "lore", columnDefinition = "TEXT")
    private String lore;

    @Column(name = "goal", columnDefinition = "TEXT")
    private String goal;

    @Column(name = "appearance", columnDefinition = "TEXT")
    private String appearance;

    @Column ( name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column ( name = "image_url")
    private String imageUrl;

    @Column (name = "grade")
    private String grade = "4";


    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private CharacterStatus status;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterItem> inventory = new ArrayList<>();

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private CursedTechnique technique;

}
