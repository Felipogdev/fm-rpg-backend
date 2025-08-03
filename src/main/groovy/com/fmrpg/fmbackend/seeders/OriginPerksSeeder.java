package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterOrigin;
import com.fmrpg.fmbackend.entities.characterpkg.OriginPerks;
import com.fmrpg.fmbackend.repositories.CharacterOriginRepository;
import com.fmrpg.fmbackend.repositories.OriginPerksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OriginPerksSeeder implements CommandLineRunner {

    private final CharacterOriginRepository characterOriginRepository;
    private final OriginPerksRepository originPerksRepository;

    @Override
    public void run(String... args) throws Exception {

        List<String> originNames = List.of(
                "Inato", "Derivado", "Restringido", "Feto Amaldiçoado Híbrido",
                "Sem Técnica", "Corpo Amaldiçoado Mutante",
                "Herdado Clã Gojo", "Herdado Clã Zenin", "Herdado Clã Inumaki", "Herdado Clã Kamo"
        );

        Map<String, CharacterOrigin> origins = originNames.stream()
                .map(characterOriginRepository::findByName)
                .collect(Collectors.toMap(CharacterOrigin::getName, Function.identity()));

        CharacterOrigin inato = origins.get("Inato");
        CharacterOrigin derivado = origins.get("Derivado");
        CharacterOrigin restringido = origins.get("Restringido");
        CharacterOrigin fetoAmaldicoado = origins.get("Feto Amaldiçoado Híbrido");
        CharacterOrigin semTecnica = origins.get("Sem Técnica");
        CharacterOrigin corpoAmaldicoado = origins.get("Corpo Amaldiçoado Mutante");
        CharacterOrigin gojo = origins.get("Herdado Clã Gojo");
        CharacterOrigin zenin = origins.get("Herdado Clã Zenin");
        CharacterOrigin inumaki = origins.get("Herdado Clã Inumaki");
        CharacterOrigin kamo = origins.get("Herdado Clã Kamo");

        List<OriginPerks> perks = new ArrayList<>(
                List.of(
                        new OriginPerks("Bônus em Atributo", "Um inato aumenta o valor de um atributo em 2 pontos e o de outro em 1 ponto.", inato),
                        new OriginPerks("Talento Natural", "Um inato é talentoso, tendo a energia amaldiçoada como algo natural para si. Você recebe um Talento a sua escolha no 1º nível. Além disso, uma única vez a partir do 4º nível, você pode escolher receber um talento adicional a sua escolha ao subir de nível.", inato),
                        new OriginPerks("Marca Registrada", "Uma técnica é inata e exclusiva para si, o que o torna naturalmente mais familiar com ela. Sendo capaz de usá-la de diferentes formas, uma delas se destaca como a registrada. Você recebe um Feitiço adicional, o qual terá o seu custo reduzido em 1PE.", inato),
                        new OriginPerks("Bônus em Atributo", "Um derivado aumenta o valor de um atributo em 2 pontos e o de outro em 1 ponto.", derivado),
                        new OriginPerks("Energia Antinatural", "Sua energia deriva de uma fonte anormal e, por isso, tem traços únicos próprios dela. Você recebe uma Aptidão Amaldiçoada de Aura, a qual você deve atender os requisitos. Além disso, você possui uma pequena reserva oculta de energia no seu âmago, da qual pode extrair quando necessário: como uma Ação Bônus, dentro de combate, você pode recuperar um valor de energia amaldiçoada igual ao dobro do seu bônus de treinamento. Você pode usar essa característica uma vez por dia.", derivado),
                        new OriginPerks("Desenvolvimento Inesperado", "O desenvolvimento de um Derivado é inesperado, podendo surpreender e ir além do esperado. A cada quatro níveis, você recebe um ponto de atributo adicional e aumenta seu limite de atributo para o atributo escolhido em 1.", derivado) ,
                        new OriginPerks("Bônus em Atributo", "Um restringido tem os seus valores de Força, Destreza e Constituição aumentados em 1, além de receber 2 pontos adicionais para distribuir entre os seus atributos físicos.", restringido),
                        new OriginPerks("Físico Abençoado", "Seu físico é esculpido de maneira única, potencializando suas capacidades. Seu Deslocamento aumenta em 3 metros, você se torna imune a doenças mundanas e recebe vantagem em testes de resistência contra venenos. Em um descanso curto, você adiciona metade do seu bônus de treinamento à quantidade de dados curados. Além disso, você recebe acesso à especialização Restringido.", restringido),
                        new OriginPerks("Ápice Corporal Humano", "Seu corpo tem um potencial extraordinário, sendo capaz de alcançar o ápice das capacidades físicas humanas. Seu limite de atributo para Força, Destreza e Constituição é 30 ao invés de 20. Além disso, a cada 6 níveis, escolha um desses atributos para receber +2 em seu valor. Sempre que realizar um teste de Atletismo para erguer peso ou saltar distâncias, dobre o limite de peso ou a distância saltada.", restringido),
                        new OriginPerks("Resiliência Imediata", "Seu corpo é mais resistente do que o padrão humano, permitindo-o encarar a dor facilmente. Uma quantidade de vezes igual ao seu bônus de treinamento, ao receber dano, você pode escolher reduzir esse dano em um valor igual à metade do seu nível (mínimo 1) multiplicado por 5. Alternativamente, você pode escolher gastar um uso dessa habilidade para evitar um desmembramento. Você recupera os usos após um descanso longo.", restringido),
                        new OriginPerks("Bônus em Atributo", "Um sem técnica recebe 4 pontos adicionais para distribuir entre seus atributos, com um máximo de 3 pontos no mesmo atributo.", semTecnica),
                        new OriginPerks("Estudos Dedicados", "Um sem técnica se dedica muito em seus estudos, tornando-se treinado em 2 perícias à sua escolha.", semTecnica),
                        new OriginPerks("Empenho Implacável", "Para compensar pela falta de uma técnica, você se empenha de maneira implacável. Conforme sobe de nível, um sem técnica recebe os seguintes benefícios: Nível 1: um talento ou aptidão amaldiçoada, à sua escolha. Nível 3: +1 em 2 perícias à sua escolha. Nível 6: uma habilidade de especialização adicional. Nível 10: um talento ou aptidão amaldiçoada, à sua escolha. Nível 13: +2 em 2 perícias à sua escolha. Nível 15: uma habilidade de especialização adicional. Nível 17: +3 em 2 perícias à sua escolha. Nível 19: uma habilidade de especialização e um talento adicional. Além disso, no 4° nível você recebe acesso ao Novo Estilo da Sombra e a aptidão amaldiçoada Domínio Simples, desde que atenda aos seus requisitos.", semTecnica),
                        new OriginPerks("Novo Estilo da Sombra", "Você adiciona ao seu Perfil Amaldiçoado o Novo Estilo da Sombra como equivalente à sua técnica amaldiçoada. Sempre que utilizar a aptidão amaldiçoada Domínio Simples, você pode imbuí-la com uma Técnica de Estilo. No começo de cada turno, caso esteja com o Domínio Simples ativo, você pode trocar a Técnica de Estilo em uso. Ao aprender o Novo Estilo da Sombra, você recebe uma Técnica de Estilo. Nos níveis 8, 12, 16 e 20, recebe uma técnica de estilo adicional. Se aprender o Domínio Simples em um nível superior, recebe um número de técnicas igual ao seu nível de personagem.", semTecnica),
                        new OriginPerks("Bônus em Atributo", "Um corpo amaldiçoado mutante recebe 2 pontos adicionais para distribuir entre seus atributos.", corpoAmaldicoado),
                        new OriginPerks("Forma de Vida Sintética", "Você é uma forma de vida artificial. É imune a dano venenoso e à condição envenenado, mas não recebe os efeitos de refeições nem de itens do tipo Medicina.", corpoAmaldicoado),
                        new OriginPerks("Mutação Abrupta", "Você passou por uma abrupta mutação que lhe concedeu a capacidade de produzir energia amaldiçoada por si mesmo, ser consciente e ter sua essência dividida em diferentes núcleos. Você inicia com três núcleos, devendo escolher um como seu Núcleo Primário. Em combate, alternar o núcleo ativo é uma Ação Bônus.Criar, desenvolver e utilizar os núcleos possui vários detalhes, explicados na seção" +  "Núcleos Múltiplos", corpoAmaldicoado),
                        new OriginPerks("Bônus em Atributo", "Um membro do clã Zenin aumenta o valor de um atributo em 2 pontos e o de outro em 1 ponto.", zenin),
                        new OriginPerks("Treinamentos de Clã", "Você se torna treinado em 2 perícias quaisquer. Alternativamente, pode escolher se tornar especialista em uma única perícia ao invés de receber treinamento em duas.", zenin),
                        new OriginPerks("Foco no Poder","O clã Zenin se dedica completamente ao poder e aprimoramento das suas técnicas. No 1º nível, você pode escolher um Feitiço para ser um Feitiço Focado. Um Feitiço Focado pode: causar um dado de dano a mais, curar um dado de vida a mais, ter o dobro do alcance ou ter a dificuldade do teste para resistir aumentada em um valor igual ao seu bônus de treinamento. Nos níveis 5, 10, 15 e 20, você pode escolher outro Feitiço para ser um Feitiço Focado.", zenin),
                        new OriginPerks("Bônus em Atributo", "Aumenta em 2 a Inteligência ou Sabedoria, e em 1 o atributo que não foi escolhido.", gojo),
                        new OriginPerks("Treinamentos de Clã", "Você se torna treinado em 2 perícias entre Feitiçaria, Percepção e Intuição. Alternativamente, pode escolher se tornar especialista em uma dessas perícias ao invés de receber treinamento em duas.", gojo),
                        new OriginPerks("Potencial Lendário","Ser parte do clã Gojo confere um potencial de energia extremo, juntamente de uma facilidade para desenvolver feitiços. Em todo nível par, você recebe 1 ponto de energia amaldiçoada adicional. Além disso, você recebe 1 Feitiço adicional no 1º nível, e mais um nos níveis 5, 10, 15 e 20.",gojo),
                        new OriginPerks("Bônus em Atributo", "Aumenta em 2 a Inteligência ou Presença, e em 1 o atributo que não foi escolhido.", inumaki),
                        new OriginPerks("Treinamentos de Clã", "Você se torna treinado em 2 perícias entre Feitiçaria, Percepção e Intuição. Alternativamente, pode escolher se tornar especialista em uma dessas perícias ao invés de receber treinamento em duas.", inumaki),
                        new OriginPerks("Olhos de Cobra e Presas","Os membros do clã Inumaki possuem uma marca única ao redor da boca, que se assemelha aos olhos de uma cobra e presas. Uma quantidade de vezes igual ao seu bônus de treinamento, você pode dar o comando de uma ação bônus para um aliado, que pode executá-la como reação. Você recupera os usos após um descanso longo.",inumaki),
                        new OriginPerks("Bônus em Atributo", "Aumenta em 2 a Constituição ou Sabedoria, e em 1 o atributo que não foi escolhido.", kamo),
                        new OriginPerks("Treinamentos de Clã", "Você se torna treinado em 2 perícias entre Atletismo, Medicina e Persuasão. Ao invés de receber treinamento em 2 perícias, pode escolher se tornar especialista em uma.", kamo),
                        new OriginPerks("Valor do Sangue","Os membros do Clã Kamo compreendem o valor do sangue, o que lhes confere maior vitalidade. Sempre que subir de nível, sua vida máxima aumenta em 1 ponto adicional. A partir do nível 10, você soma seu modificador de Constituição ao total de vida. Caso, ao subir de nível, você role para aumentar sua vida máxima e o valor obtido seja menor do que a média, você pode rolar novamente e ficar com o maior valor.",kamo),
                        new OriginPerks("Bônus em Atributo", "Um feto amaldiçoado híbrido aumenta o valor de um atributo em 2 pontos e o de outro em 1 ponto.", fetoAmaldicoado),
                        new OriginPerks("Herança Maldita", "Como um híbrido entre humano e maldição, você carrega uma herança amaldiçoada. Toda cura proveniente de energia reversa é reduzida pela metade. Caso obtenha uma habilidade de energia reversa de cura, pode usá-la tratando a energia reversa como energia amaldiçoada, curando o valor completo. Ao invés de gastar 1 ponto de energia reversa, gasta diretamente 2 pontos de energia amaldiçoada.", fetoAmaldicoado),
                        new OriginPerks("Físico Amaldiçoado", "Sendo meio maldição, seu físico é único, desenvolvendo um corpo com propriedades especiais. Você recebe uma Característica de Anatomia (listadas na próxima página). A cada 5 níveis, seu corpo desenvolve mais, dando-lhe outra característica de anatomia.", fetoAmaldicoado),
                        new OriginPerks("Vigor Maldito","Você pode, uma vez por descanso longo, usar uma ação bônus para recuperar pontos de vida igual a 5 + seu modificador de Constituição. Nos níveis 4, 8 e 12, recebe um uso adicional desta característica, assim como o valor base da cura aumenta em 5. Caso possua mais de um uso, pode escolher gastar mais usos simultaneamente para aumentar a cura.",fetoAmaldicoado)
                        )
        );

        if (originPerksRepository.count() == 0) {
            originPerksRepository.saveAll(perks);
        }
    }
}
