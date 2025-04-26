package feiticeiros.example.fmbackend.characterstatus;

import jakarta.persistence.OneToOne;

import java.util.UUID;

public class StatusEntity {

    UUID characterId;

    short hp;
    short current_hp;

    short curse_energy;
    short current_curse_energy;

    byte con;
    byte dex;
    byte cha;
    byte inte;
    byte wis;
    byte str;

    byte armor_class;
    byte moviment;
    byte soul;


}
