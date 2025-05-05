package feiticeiros.example.fmbackend.itempackages.characteritems.characteritem;

import feiticeiros.example.fmbackend.characterpackages.character.CharacterEntity;
import feiticeiros.example.fmbackend.itempackages.itemtemplates.ItemTemplateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "character_items")
@Getter
@Setter
public class CharacterItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String custom_name;

    @Column(columnDefinition = "TEXT")
    private String custom_description;

    private Integer custom_tier;

    private Integer custom_cost;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private ItemTemplateEntity template;
}

