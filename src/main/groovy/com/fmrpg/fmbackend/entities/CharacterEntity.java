package com.fmrpg.fmbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;

    @Column (name = "name")
    private String name;

    @Column(name = "level")
    private Integer level = 1;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Column (name = "origin")
    @Enumerated(EnumType.STRING)
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

}
