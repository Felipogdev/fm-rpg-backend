package com.fmrpg.fmbackend.entities;

import com.fmrpg.fmbackend.enums.AttributeType;
import com.fmrpg.fmbackend.enums.SkillProficiency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "related_attribute")
    private AttributeType relatedAttribute;
}