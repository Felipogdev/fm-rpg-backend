package com.fmrpg.fmbackend.seeders;


import com.fmrpg.fmbackend.entities.Skills;
import com.fmrpg.fmbackend.enums.AttributeType;
import com.fmrpg.fmbackend.repositories.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SkillSeeder implements CommandLineRunner {

    private final SkillsRepository skillsRepository;

    @Override
    public void run(String ... args) {
        if (skillsRepository.count() > 0) return;

        List<Skills> skills = List.of(
                new Skills(null, "Acrobacia", AttributeType.DEX),
                new Skills(null, "Astúcia", AttributeType.INT),
                new Skills(null, "Atletismo", AttributeType.STR),
                new Skills(null, "Direção", AttributeType.INT),
                new Skills(null, "Enganação", AttributeType.CHA),
                new Skills(null, "Feitiçaria", AttributeType.INT),
                new Skills(null, "Fortitude", AttributeType.CON),
                new Skills(null, "Furtividade", AttributeType.DEX),
                new Skills(null, "História", AttributeType.INT),
                new Skills(null, "Integridade", AttributeType.CON),
                new Skills(null, "Intimidação", AttributeType.CHA),
                new Skills(null, "Intuição", AttributeType.WIS),
                new Skills(null, "Investigação", AttributeType.INT),
                new Skills(null, "Luta", AttributeType.STR_DEX),
                new Skills(null, "Medicina", AttributeType.WIS),
                new Skills(null, "Ocultismo", AttributeType.WIS),
                new Skills(null, "Ofício", AttributeType.INT),
                new Skills(null, "Percepção", AttributeType.WIS),
                new Skills(null, "Performance", AttributeType.CHA),
                new Skills(null, "Persuasão", AttributeType.CHA),
                new Skills(null, "Pontaria", AttributeType.STR_DEX),
                new Skills(null, "Prestidigitação", AttributeType.DEX),
                new Skills(null, "Reflexos", AttributeType.DEX),
                new Skills(null, "Religião", AttributeType.INT),
                new Skills(null, "Sobrevivência", AttributeType.WIS),
                new Skills(null, "Tecnologia", AttributeType.INT),
                new Skills(null, "Vontade", AttributeType.WIS)
                );

        skillsRepository.saveAll(skills);
        System.out.println(" Skills seeded com sucesso!");
    }
}
