package com.fmrpg.fmbackend.entities.itemenchantments;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Inheritance
public class AbstractItemEnchantment {

    @Id
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private AbstractItemEnchantment preRequiremnt;
}
