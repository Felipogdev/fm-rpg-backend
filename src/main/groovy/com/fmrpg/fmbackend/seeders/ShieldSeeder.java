package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.Shield;
import com.fmrpg.fmbackend.repositories.ShieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShieldSeeder implements CommandLineRunner {

    private final ShieldRepository shieldRepository;

    @Override
    public void run(String... args) throws Exception {

        if (shieldRepository.count() > 0) return;

        List<Shield> uniforms = List.of(
                new Shield(1, 0, "Escudo Leve", 1, 1,
                "Um pequeno escudo, leve em peso e capaz de auxiliar na defesa de golpes mais simples."),
                new Shield(2, -1, "Escudo Pesado", 1, 2,
                        "Um escudo maior e pesado, cobrindo uma parte considerável do corpo, em troca de uma certa dificuldade no seu manejo."),
                new Shield(4, -2, "Escudo Colossal", 1, 3,
                        "Um escudo massivo e colossal, cobrindo todo o corpo e com um uso difículo, principalmente pelo seu pesado exagero. É necessário pelo menos 18 de força para utilizar o escudo colossal."),
                new Shield(1, 0, "Escudo Pequeno", 1, 2,
                        "Um esucdo pequeno, otimizado para ser preso ao braço, mantendo uma mão livre enquanto dá um impulso na guarda. O escudo leve não ocupa uma das suas mãos, permitindo o manejo de armas de duas mãos enquanto estiver o usando.")
        );

        shieldRepository.saveAll(uniforms);
    }
}
