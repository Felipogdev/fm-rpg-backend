package feiticeiros.example.fmbackend.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class ItemAbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Min(1)
    @Max(5) //Tratar grau especial como tier 5
    private Integer tier;

    private Integer inventory_space;
}
