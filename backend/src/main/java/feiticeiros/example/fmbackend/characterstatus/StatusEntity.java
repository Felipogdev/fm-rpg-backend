package feiticeiros.example.fmbackend.characterstatus;

import feiticeiros.example.fmbackend.character.CharacterEntity;
import jakarta.persistence.*;
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

    short hp;
    short current_hp;

    short curse_energy;
    short current_curse_energy;

    byte soul;
    byte current_soul;

    //@Min(0)
    //@Max(30)
    byte con;
    byte dex;
    byte cha;
    byte inte;
    byte wis;
    byte str;

    byte armor_class;
    byte moviment;
}
