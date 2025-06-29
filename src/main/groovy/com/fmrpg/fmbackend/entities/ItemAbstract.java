package com.fmrpg.fmbackend.entities;


import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.*;

@Entity
@Table(name= "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    ItemCategory itemCategory;

    @Column(name = "name")
    String name;

    @Column(name = "weight")
    Integer weight;

    @Column(name = "cost")
    Integer cost;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

}
