package com.fmrpg.fmbackend.entities;


import com.fmrpg.fmbackend.enums.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;


@MappedSuperclass
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
}
