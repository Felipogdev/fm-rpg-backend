package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.CharacterClass;
import com.fmrpg.fmbackend.entities.CharacterOrigin;
import com.fmrpg.fmbackend.repositories.CharacterClassRepository;
import com.fmrpg.fmbackend.repositories.CharacterOriginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterDataSeeder implements CommandLineRunner {

    private final CharacterClassRepository classRepository;
    private final CharacterOriginRepository originRepository;

    public CharacterDataSeeder(CharacterClassRepository classRepository, CharacterOriginRepository originRepository) {
        this.classRepository = classRepository;
        this.originRepository = originRepository;
    }

    @Override
    public void run(String... args) {
        if (classRepository.count() == 0) {
            classRepository.saveAll(List.of(
                    new CharacterClass(null, "Lutador", "O foco é combate físico ou com armas marciais, sendo resistente, móvel e potente, além de estar armado com várias manobars que se tornam disponíveis enquanto se monta um combo."),
                    new CharacterClass(null, "Especialista em Combate", "Leva o combate como uma arte a se estudar e praticar, lutando com estratégia, versatilidade e letalidade. Tem várias possibilidades de artes de combate e maneiras de lutar."),
                    new CharacterClass(null, "Especialista em Técnica", "Estuda ao máximo a energia amaldiçoada, dominando-a e conseguindo até mesmo alterar os seus fundamentos com um controle avançado. Perfeito para aqueles que querem se dedicar a sua técnica."),
                    new CharacterClass(null, "Controlador", "Cria corpos amaldiçoados ou domina shikigamis e os controla com uma maestria superior, conseguindo melhorar suas invocações como um todo e dominar o campo de batalha com elas."),
                    new CharacterClass(null, "Suporte", "Dedicado a apoiar os seus aliados, garantir a integridade e saúde deles, motivá-los e abrir novas possibilidades. Tem grande potencial de cura, assim como outras maneiras de fortalecer os aliados em campo."),
                    new CharacterClass(null, "Restringido", "Foge do padrão ao não possuir energia amaldiçoada, mas ter um desenvolvimento físico anormal, levando o seu corpo e as suas técnicas marciais para outro patamar, além de interagir de maneira peculiar com usuários de energia.")
            ));
        }

        if (originRepository.count() == 0) {
            originRepository.saveAll(List.of(
                    new CharacterOrigin(null, "Inato", "O Inato é possivelmente a origem mais comum no mundo do Jujutsu, sendo aqueles que nasceram com a afinidade para usar energia amaldiçoada e com uma técnica própria, a qual se manifesta em algum ponto, sendo preciso apenas a treinar e desenvolver. Por ser única no mundo, a sua técnica é imprevisível e você tem o potencial de se inovar cada vez mais."),
                    new CharacterOrigin(null, "Derivado", "Existem raros casos de pessoas cuja energia e técnica amaldiçoada derivaram de uma fonte alternativa, a qual veio em momentos posteriores da sua vida e, possivelmente, de maneira não natural, seja pelo consumo de um objeto amaldiçoado, alguma alteração na alma, experimentou o que for, aquilo para sempre mudou a pessoa."),
                    new CharacterOrigin(null, "Restringido", "Certas pessoas, no meio dos feiticeiros, nascem com uma quantidade quase nula de energia amaldiçoada, recebendo em troca um físico mais desenvolvido e capacidades especiais, além de compensar com o uso magistral de ferramentas amaldiçoadas. No começo, são considerados fracassos, pois possuem mais dificuldade em tudo aquilo que é fácil para os outros feiticeiros, porém, ao alcançar um nível nulo de energia, se tornam anomalias devastadoras."),
                    new CharacterOrigin(null, "Feto Amaldiçoado Híbrido", "Um feto amaldiçoado é um espírito amaldiçoado em estado embrionário, o qual, em condições específicas, pode acabar se tornando um híbrido entre humano e maldição. Isso resulta em uma constituição e anatomia única, além da capacidade de dominar o jujutsu e suas técnicas de maneira natural."),
                    new CharacterOrigin(null, "Sem Técnica", "Nem todos são abençoados com uma técnica amaldiçoada, possuindo apenas as capacidades básicas da energia amaldiçoada. Isso é um limitador, mas sempre é possível se dedicar e empenhar o suficiente para compensar pela falta de uma técnica. E é assim a vida daqueles sem técnica, precisando de se dedicar ao limite."),
                    new CharacterOrigin(null, "Corpo Amaldiçoado Mutante", "Um corpo amaldiçoado mutante passou por um processo que concede ao corpo criado uma consciência própria, uma fonte renovável de energia amaldiçoada e a capacidade de possuir vários núcleos, com cada um possuindo suas próprias características únicas, alternando o seu funcionamento e foco dentro e fora de combate."),
                    new CharacterOrigin(null, "Herdado Clã Gojo", "O Clã Gojo descende de um lendário feiticeiro antigo de Jujutsu, Michizane Sugawara, e tem como técnicas herdadas o Ilimitado e os Seis Olhos, que juntos tem um poder enorme. O membro de maior destaque é Satoru Gojo, o feiticeiro mais forte, cujo poder sozinho é capaz de manter o clã Gojo entre os maiores e mais respeitados."),
                    new CharacterOrigin(null, "Herdado Clã Inumaki", "O Clã Inumaki é uma das várias famílias menores. Embora não sejam considerados um dos clãs maiores, sua técnica amaldiçoada, Fala Amaldiçoada, é bem respeitada. Possuem um sigilo característico da família, que são os emblemas ao redor da boca do usuário da técnica. O membro de maior destaque é Toge Inumaki."),
                    new CharacterOrigin(null, "Herdado Clã Kamo", "O Clã Kamo valoriza grandemente os laços de sangue, e herdar a sua técnica é o foco. A sua técnica herdada é a Manipulação de Sangue, herdada pelo membro de maior destaque, que é Noritoshi Kamo. Sua técnica é admirada pelo equilíbrio fornecido e por ser perfeita para aqueles que valorizam o sangue."),
                    new CharacterOrigin(null, "Herdado Clã Zenin", "O Clã Zenin incorpora todos os valores nobres de um clã maior, acreditando que técnicas amaldiçoadas poderosas são mais importantes do que tudo. Entretanto, às vezes isso acarreta em problemáticas diante aqueles feiticeiros que não se desenvolvem muito. Possuem várias técnicas herdadas, com grande variedade, mas mantendo o poder e potencial elevado.")
            ));
        }
    }
}
