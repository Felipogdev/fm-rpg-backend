package feiticeiros.example.fmbackend.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer cost;

    private ItemType type;

    @Min(1)
    @Max(5) //Tratar grau especial como tier 5
    private Integer tier;

}
