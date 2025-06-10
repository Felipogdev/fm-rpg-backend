package com.fmrpg.fmbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;
import jakarta.persistence.*;
import lombok.*;

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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;

    @Column (name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Column (name = "origin")
    @Enumerated(EnumType.STRING)
    private CharacterOrigin characterOrigin;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column ( name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column ( name = "image_url")
    private String imageUrl;

    @PrePersist
    private void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.createdAt == null) {
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }

        if (this.level == null) {
            this.level = 1;
        }
    }
}
