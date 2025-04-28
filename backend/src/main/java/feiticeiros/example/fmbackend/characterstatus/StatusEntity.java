package feiticeiros.example.fmbackend.characterstatus;

import feiticeiros.example.fmbackend.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "status")
public class StatusEntity {

    @Id
    private UUID character_id;

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private CharacterEntity character;

    Integer max_hp;
    Integer current_hp;

    @Max(100)
    Integer max_curse_energy;
    Integer current_curse_energy;

    Integer soul;
    Integer current_soul;

    //@Min(4)
    //Min Sem o bgl do mechamaru é 8
    //@Max(32)
    Integer con;
    Integer dex;
    Integer cha;
    Integer inte;
    Integer wis;
    Integer str;

    Integer armor_class;
    Integer moviment;
}
