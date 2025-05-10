package feiticeiros.example.fmbackend.itempackages.itemtemplates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class ItemTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private Integer inventory_space;

    // Cinco (Normal, sem maldição),Quatro, Três, Dois, Um, Especial
    private Integer tier;

    //@ManyToOne
    //@JoinColumn(name = "character_id", nullable = false)
    //private CharacterEntity character;
}
