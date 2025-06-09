package com.fmrpg.fmbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @Column(name = "user_id", nullable = false)
    private User user;

    @Column (name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "class")
    private String characterClass; // TODO: Botar somente as classes do sistema

    @Column (name = "origin")
    private String origin; // TODO: Colocar as origens do sistema

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column ( name = "created_at", updatable = false)
    private Timestamp createdAt;

    private void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.createdAt == null) {
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }
    }



}
