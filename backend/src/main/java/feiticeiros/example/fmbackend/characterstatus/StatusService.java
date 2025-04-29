package feiticeiros.example.fmbackend.characterstatus;

import feiticeiros.example.fmbackend.character.CharacterClasses;
import feiticeiros.example.fmbackend.character.CharacterEntity;
import feiticeiros.example.fmbackend.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public StatusEntity createStatus(CharacterEntity characterEntity) {
        if (characterEntity.getId() == null) {
            characterEntity = characterRepository.save(characterEntity);
        }

        StatusEntity status = new StatusEntity();
        status.setCharacter(characterEntity);
        return statusRepository.save(status);
    }




    //Provavelmente não usar essa função na primeira versão, porque não está calculado automatico de forma corretamente devido ao mod de CON
    public void setStatusAutomatic(CharacterEntity characterEntity, StatusEntity statusEntity, String techniqueAttribute) {

        CharacterClasses characterClass = characterEntity.getCharacter_class();
        int hpBase = characterClass.getHpBase();
        int hpPerLevel = characterClass.getHpPerLevel();
        int cursedEnergyPerLevel = characterClass.getCursedEnergyPerLevel();

        int level = characterEntity.getLevel();
        int con = statusEntity.getCon();

        // Cálculo de HP
        int maxHp = hpBase + con + (hpPerLevel + con) * (level - 1);
        statusEntity.setMax_hp(maxHp);

        // Cálculo de Energia Amaldiçoada
        switch (characterClass) {
            case ESPECIALISTA_TECNICA, CONTROLADOR, SUPORTE -> {
                int techniqueMod = switch (techniqueAttribute) {
                    case "int" -> statusEntity.getInte();
                    case "char" -> statusEntity.getCha();
                    case "wis" -> statusEntity.getWis();
                    default -> throw new IllegalArgumentException("Atributo de técnica desconhecido: " + techniqueAttribute);
                };
                int maxCursedEnergy = (cursedEnergyPerLevel + techniqueMod) * level;
                statusEntity.setMax_curse_energy(maxCursedEnergy);
            }
            default -> {
                int maxCursedEnergy = cursedEnergyPerLevel * level;
                statusEntity.setMax_curse_energy(maxCursedEnergy);
            }
        }
    }

    public void setStatusManual(StatusEntity statusEntity, int maxHp, int maxCursedEnergy) {
        statusEntity.setMax_hp(maxHp);
        statusEntity.setMax_curse_energy(maxCursedEnergy);
    }

}
