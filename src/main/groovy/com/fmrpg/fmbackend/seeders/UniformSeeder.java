package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.Uniform;
import com.fmrpg.fmbackend.repositories.UniformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UniformSeeder implements CommandLineRunner {

    private final UniformRepository uniformRepository;

    @Override
    public void run(String... args) throws Exception {

        if (uniformRepository.count() > 0) return;

        List<Uniform> uniforms = List.of(
                new Uniform(2, 0, "Revestimento Leve", 1, 1,
                        "Um revestimento leve é colocado no uniforme, concedendo-o um leve reforço defensivo."),
                new Uniform(4, -2, "Revestimento Médio", 1, 2,
                        "O uniforme tem uma quantidade demorada de revestimentos colocados, através de algumas placas e camadas adicionais, o que dá um peso considerável ao uniforme."),
                new Uniform(6, -4, "Revestimento Robusto", 1, 3,
                        "Um revestimento pesado é implementado no uniforme, com placas fortes, camadas densas e a adição de peças que se assemelham a armaduras ou coletes, o que o dá um peso equivalente."),
                new Uniform(1, 0, "Sob Medida", 1, 2,
                        "O uniforme é feito sob medida, encaixando-se perfeitamente no corpo do feiticeiro, beneficiando-o em acrobacias e destacando a sua agilidade. Enquanto estiver usando um uniforme sob medida, você recebe +2 em testes de Acrobacia, Furtividade e Reflexos.")
        );

        uniformRepository.saveAll(uniforms);
    }
}
