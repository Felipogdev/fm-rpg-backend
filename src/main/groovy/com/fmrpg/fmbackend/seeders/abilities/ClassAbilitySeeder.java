package com.fmrpg.fmbackend.seeders.abilities;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterClass;
import com.fmrpg.fmbackend.entities.characterpkg.ClassAbility;
import com.fmrpg.fmbackend.repositories.CharacterClassRepository;
import com.fmrpg.fmbackend.repositories.ClassAbilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Component
@RequiredArgsConstructor
@Order(2)
public class ClassAbilitySeeder implements CommandLineRunner {

    private final ClassAbilityRepository classAbilityRepository;
    private final CharacterClassRepository characterClassRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        CharacterClass fighter = characterClassRepository.findByName("Lutador").orElseThrow();
        CharacterClass combatSpecialist = characterClassRepository.findByName("Especialista em Combate").orElseThrow();
        CharacterClass techniqueSpecialist = characterClassRepository.findByName("Especialista em Técnica").orElseThrow();
        CharacterClass support = characterClassRepository.findByName("Suporte").orElseThrow();
        CharacterClass restricted = characterClassRepository.findByName("Restringido").orElseThrow();
        CharacterClass controller = characterClassRepository.findByName("Controlador").orElseThrow();


        List<ClassAbility> fighterAbilities = new ArrayList<>(List.of(
                        // Level 2 abilities
                        new ClassAbility("Aparar Ataque", "Você rebate um ataque com outro ataque, assim conseguindo aparar um golpe. Quando for alvo de um ataque corpo a corpo, você pode gastar 1PE e sua reação para realizar uma jogada de ataque contra o atacante. Caso seu teste supere o do inimigo, você evita o ataque", 2, fighter),
                        new ClassAbility("Aparar Projéteis", "Utilizando de sua agilidade e reflexos, você consegue tentar aparar projéteis em sua direção, reduzindo o dano deles. Quando receber um ataque à distância, você pode gastar 1 PE e sua reação para tentar aparar o projétil, reduzindo o dano recebido em 2d6 + modificador de atributo-chave + bônus de treinamento.", 2, fighter),
                        new ClassAbility("Ataque Inconsequente", "Você baixa a guarda para atacar de maneira inconsequente, aumentando seu potencial de dano. Uma vez por rodada, ao realizar um ataque, você pode escolher receber vantagem na jogada de ataque e +5 na rolagem de dano dele. Porém, ao realizar um ataque inconsequente, você fica Desprevenido por 1 rodada.", 2, fighter),
                        new ClassAbility("Caminho da Mão Vazia", "Mesmo diante a possibilidade de brandir armas marciais, você decide se ater as mãos vazias e se aperfeiçoar nesse caminho. Todo ataque desarmado que você realizar causa dano adicional igual ao seu bônus de treinamento e você soma metade do seu bônus de treinamento em jogadas de ataque desarmados.", 2, fighter),
                        new ClassAbility("Complementação Marcial", "Suas habilidades marciais complementam certas manobras, deixando-as mais eficientes. Enquanto estiver desarmado ou empunhando uma arma marcial, você recebe um bônus de +2 em testes para Desarmar, Derrubar ou Empurrar, assim como para resistir a esses efeitos.", 2, fighter),
                        new ClassAbility("Deboche Desconcertante", "Cheio de si, você consegue debochar de um inimigo de uma maneira que o desconcerta. Como uma Ação Bônus, escolha uma criatura que possa te ver ou ouvir: realize um teste de Intimidação contra um teste de Vontade dela, no qual você recebe um bônus de +2. Caso você suceda, a criatura recebe uma penalidade igual ao seu bônus de treinamento em todos os testes que ela realizar até o começo do seu próximo turno.", 2, fighter, "Treinado em Intimidação"),
                        new ClassAbility("Dedicação em Arma", "Ao invés de contar apenas com seus punhos, você se dedica a certas armas. Escolha três armas para serem suas Armas Dedicadas, as quais não podem possuir as propriedades Duas Mãos ou Pesada. Suas armas escolhidas passam a ser contadas como marciais, se não forem, e enquanto empunhar uma Arma Dedicada, o dano dela aumenta em 1 nível.", 2, fighter),
                        new ClassAbility("Esquiva Rápida", "Com agilidade, você se prepara para esquivar de ataques. Como uma Ação Bônus, realize um teste de Acrobacia contra a Atenção de um inimigo dentro do seu alcance corpo a corpo. Caso suceda no teste, o alvo recebe metade do seu modificador de Destreza como penalidade em jogadas de ataque feitas contra você até o começo do seu próximo turno.", 2, fighter),
                        new ClassAbility("Finta Melhorada", "Você desenvolva sua finta para que se torne mais eficiente e se adaptar ao seu corpo. Você pode optar por utilizar Destreza ao invés de Presença em testes de Enganação para fintar. Além disso, acertar um inimigo desprevenido pela sua finta causa um dado de dano adicional.", 2, fighter),
                        new ClassAbility("Impacto Misto", "Você consegue misturar o uso de armas adequadas com seus ataques desarmados. Quando acertar uma criatura com um ataque com arma marcial, você recebe +2 em jogadas de ataque e dano desarmados até o começo do seu próximo turno.", 2, fighter),
                        new ClassAbility("Kiai Intimidador", "Sendo a exteriorização da energia e força corporal, um kiai é liberado diante um bom golpe, intimidando com um grito. Uma vez por rodada, quando conseguir um crítico em um ataque corpo a corpo você pode, como uma ação livre, realizar um teste de Intimidação contra Vontade do alvo e, caso suceda, ela fica Abalada por uma rodada.", 2, fighter),
                        new ClassAbility("Mãos Amaldiçoadas", "Como um feiticeiro, você consegue incorporar o jujutsu em seu combate a curta distância. Quando utilizar um Feitiço ofensivo com alcance de Toque, você pode substituir a jogada de ataque de técnica por uma jogada de ataque corpo a corpo e, também, somar seu modificador de Força ou Destreza no total.", 2, fighter),
                        new ClassAbility("Puxar um Ar", "Você consegue respirar fundo e puxar as forças guardadas em seu interior para continuar lutando. Você pode, como uma Ação Bônus, realizar uma rolagem do seu dano desarmado e se curar nesse valor. Esta habilidade pode ser usada uma quantidade de vezes igual ao seu bônus de treinamento, por descanso curto ou longo.", 2, fighter),
                        new ClassAbility("Quebrando Tudo", "O cenário e ambiente ao seu redor conta com várias armas e, lutando para quebrar tudo, você aprimora seu uso de armas improvisadas. Como parte de um ataque, você pode agarrar um objeto pequeno ou menor adjacente a você. Objetos usados de arma improvisada recebem +1d no dano e são considerados armas marciais.", 2, fighter),
                        new ClassAbility("Resistir", "Você pode utilizar da energia para fortalecer seu corpo e resistir com mais eficiência. Quando realizar um teste de resistência de Fortitude ou Reflexos, você pode gastar até 2PE para receber um bônus de +2 para cada PE gasto.", 2, fighter),

                        // Level 4 abilities
                        new ClassAbility("Ação Ágil", "Você otimiza o seu tempo de ação. Uma vez por rodada, você pode gastar 2PE para receber uma Ação Ágil, a qual pode ser utilizada para Andar, Desengajar ou Esconder.", 4, fighter),
                        new ClassAbility("Acrobata", "Ao invés da força, você usa a agilidade para poder saltar. Você passa a utilizar Destreza como atributo para calcular sua distância de pulo, assim como pode utilizar Acrobacia no lugar de Atletismo em testes para aumentar a sua distância de salto.", 4, fighter),
                        new ClassAbility("Atacar e Recuar", "Você consegue atacar e aproveitar a brecha do golpe para se afastar do inimigo. Uma vez por turno, quando acertar uma criatura com um ataque, você pode gastar 1 PE para se mover até 4,5 metros para longe da criatura acertada. Este movimento não causa ataques de oportunidade.", 4, fighter, "Esquiva Rápida"),
                        new ClassAbility("Brutalidade", "Existe uma brutalidade guardada no seu interior, a qual pode ser canalizada como uma fúria para combate. Como uma Ação Livre, você pode gastar 2PE para adentrar no estado de Brutalidade: enquanto nesse estado, você recebe +2 em jogadas de ataque corpo a corpo e dano.", 4, fighter),
                        new ClassAbility("Defesa Marcial", "Você é capaz de incorporar a leveza de seus movimentos em sua defesa. Enquanto estiver desarmado ou empunhando uma arma marcial, você soma 1 + metade do seu Bônus de Treinamento à sua Defesa.", 4, fighter, "Complementação Marcial"),
                        new ClassAbility("Devolver Projéteis", "Sua capacidade de aparar é aprimorada, abrindo também oportunidades para os devolver. O dado de Aparar Projéteis se torna 3d10 e soma também o seu Nível de Lutador. Caso você use Aparar Projéteis e o dano se torne nulo ou negativo, você pode devolver o projétil como parte da reação.", 4, fighter, "Aparar Projéteis"),
                        new ClassAbility("Fluxo", "Conforme se empolga, você cada vez mais se aproxima de entrar 'na zona', um estado de completo foco e imersão na luta. A cada nível de empolgação que você subir, você recebe +1 em rolagens de acerto e de dano, até um máximo de +4, com nível de empolgação 5.", 4, fighter),
                        new ClassAbility("Fúria da Vingança", "Seus aliados são importantes, e você irá vingá-los caso necessário. Ao ver um personagem aliado (Invocações não são consideradas) chegar a 0 pontos de vida e cair, você recebe os seguintes benefícios durante uma rodada: seus ataques causam 4 de dano adicional; sua Defesa aumenta em 2; você recebe +2 em TRs de Fortitude e Vontade.", 4, fighter),
                        new ClassAbility("Imprudência Motivadora", "Em certos momentos, ser imprudente e se desafiar o motiva a triunfar. Ao iniciar uma cena de combate, você pode escolher lutar com uma restrição auto imposta, escolha um dos seus sensos ou membros (como não usar a visão ou não usar uma das pernas), até o final da cena, recebe as mesmas penalidades de perder um membro.", 4, fighter),
                        new ClassAbility("Músculos Desenvolvidos", "Sua força o fez ter músculos desenvolvidos, os quais por consequência acabaram ficando mais preparados para receber golpes, sendo mais difícil o acertar de maneira efetiva. Ao obter esta habilidade, você pode optar por somar seu Modificador de Força ao invés de Destreza em sua Defesa, modificando o cálculo padrão.", 4, fighter),
                        new ClassAbility("Redirecionar Força", "Você consegue redirecionar um golpe direcionado a você, mudando o alvo. Quando um inimigo errar um ataque corpo a corpo contra você, você pode gastar 2PE e sua reação para tentar redirecionar o ataque: escolha outra criatura dentro do alcance do golpe e, caso o resultado da jogada de ataque dela seja superior à Defesa do novo alvo, ele recebe o ataque.", 4, fighter),
                        new ClassAbility("Segura Pra Mim", "Uma criatura agarrada pode ser utilizada como escudo. Quando for alvo de um ataque corpo a corpo ou uma habilidade com alvo único, você pode gastar 3 PE para tentar colocar uma criatura que esteja agarrando na frente, faça um teste de Atletismo contra o Atletismo ou Acrobacia da criatura agarrada.", 4, fighter),
                        new ClassAbility("Sobrevivente", "Como um lutador, você deve sobreviver, recuperando-se quando sente a vitalidade esvaindo. Enquanto estiver com menos da metade dos seus pontos de vida máximos, sempre que começar seu turno, você recupera 1d6 + seu modificador de Constituição em pontos de vida.", 4, fighter, "Constituição 16"),
                        new ClassAbility("Voadora", "Você consegue investir em uma voadora, acumulando potência conforme a distância aumenta. Quando realizar uma Investida, e estiver desarmado, você pode gastar 3PE para realizar uma Voadora. Caso o faça, você causa 1d8 de dano adicional para cada 3 metros que se deslocar até o alvo, limitado pelo seu modificador de Força ou Destreza.", 4, fighter),

                        // Level 6 abilities
                        new ClassAbility("Aprimoramento Marcial", "Você aprimora suas habilidades marciais para deixar mais difícil resistir as suas técnicas de Lutador. Você passa a somar metade do seu Bônus de Treinamento em sua CD de Especialização.", 6, fighter),
                        new ClassAbility("Ataque Extra", "Você consegue atacar mais rápido, otimizando seus golpes. Ao realizar a ação Atacar, você pode gastar 2 PE para atacar duas vezes ao invés de uma.", 6, fighter),
                        new ClassAbility("Brutalidade Sanguinária", "Em meio a brutalidade, o sangue pode o renovar. Enquanto no estado de Brutalidade, sempre que tiver um acerto crítico ou reduzir a vida de uma criatura a 0 ou menos, você aumenta o nível de dano dos seus ataques corpo a corpo em 1, acumulando até um limite igual ao seu bônus de treinamento.", 6, fighter, "Brutalidade"),
                        new ClassAbility("Corpo Calejado", "De tanto combater e receber golpes, todo seu corpo já está calejado e mais resistente. Você passa a adicionar metade do seu Modificador de Constituição na sua Defesa e recebe pontos de vida adicionais igual ao seu nível de Lutador.", 6, fighter),
                        new ClassAbility("Eliminar e Continuar", "Eliminar um inimigo e o ver cair serve apenas como um incentivo para continuar. Sempre que um inimigo ao qual você causou dano cair ou morrer dentro de 9 metros, você recebe 2d6 + nível de personagem + modificador de atributo-chave em PV temporários.", 6, fighter),
                        new ClassAbility("Foguete sem Ré", "Se dedicando a avançar sem olhar para trás, você consegue usar da sua energia para o impulsionar em uma investida direta. Como uma ação completa, você gasta 6 PE para se mover até uma distância igual ao dobro do seu deslocamento; sempre que passar por uma criatura durante essa investida, ela deve realizar um teste de resistência de Reflexos.", 6, fighter),
                        new ClassAbility("Golpe da Mão Aberta", "Você é capaz de realizar um ataque potente, utilizando a palma da mão. Como uma ação comum, você pode gastar 4 PE para realizar um golpe de mão aberta. Você realiza um ataque desarmado contra um alvo dentro do seu alcance corpo a corpo e, em um acerto, ele deve realizar um teste de resistência de Fortitude.", 6, fighter),
                        new ClassAbility("Ignorar Dor", "Seu desejo por uma boa luta é constante, permitindo-o até mesmo ignorar parte da dor que seja infligida em você. Você recebe redução de danos contra todos os tipos, menos alma, igual ao seu nível de empolgação atual. Contra danos físicos, a redução de dano é dobrada.", 6, fighter),
                        new ClassAbility("Manobras Finalizadoras", "Após toda uma sequência empolgante, você sabe exatamente como finalizar o seu combo com uma manobra ainda mais impactante. Você libera acesso a novas manobras, listadas no final da especialização. Ao realizar um ataque, você pode realizar uma Manobra Finalizadora.", 6, fighter),
                        new ClassAbility("Poder Corporal", "Cultivando e priorizando seu próprio corpo, você expande o poder dele. O dano de seus ataques desarmados aumenta em 2 níveis e, uma vez por rodada, ao realizar um ataque desarmado, você pode escolher realizar uma Manobra como parte do ataque, aplicando seu efeito juntamente do dano.", 6, fighter, "Caminho da Mão Vazia"),
                        new ClassAbility("Potência Superior", "A potência que você consegue colocar em suas manobras se torna superior. Quando Derrubar um inimigo com sucesso, ele também recebe 2d6 + seu modificador de Força de dano de impacto; quando Empurrar um inimigo, a distância padrão se torna 4,5 metros ao invés de 1,5 metros.", 6, fighter, "Complementação Marcial"),
                        new ClassAbility("Sequência Inconsequente", "Não se limitando a apenas um ataque, você assume uma postura inconsequente durante todo seu período de atacar. Quando utilizar Ataque Inconsequente, você passa a receber o dano adicional em todos seus ataques realizados durante o turno.", 6, fighter, "Ataque Inconsequente"),
                        new ClassAbility("Um Com a Arma", "Você começa a se tornar apenas um com as armas para as quais se dedicou. Uma quantidade de vezes igual a metade do seu nível de Lutador, por descanso curto, suas armas dedicadas conseguem superar resistência ao tipo de dano delas em um ataque.", 6, fighter, "Dedicação em Arma"),

                        // Level 8 abilities
                        new ClassAbility("Aptidões de Luta", "Você aprimora suas aptidões de energia necessárias para a luta. Ao obter esta habilidade, você pode aumentar o seu nível de aptidão em Aura ou Controle e Leitura em 1. Você pode pegar esta habilidade duas vezes, uma para cada aptidão.", 8, fighter),
                        new ClassAbility("Ataques Ressonantes", "O impacto dos seus ataques ressoa e atinge outros inimigos próximos do seu alvo. Ao realizar um ataque contra um inimigo, você pode gastar 2 pontos de energia amaldiçoada para que todos os inimigos adjacentes ao alvo, com a Defesa inferior ao resultado do seu ataque, recebam dano igual a metade do dano causado no alvo.", 8, fighter),
                        new ClassAbility("Brutalidade Aprimorada", "Aprimorando no fluxo que você impõe no seu corpo, ele te deixa ainda mais resistente. Ao entrar no estado de brutalidade, você recebe uma quantidade de pontos de vida temporários igual ao seu nível + modificador do atributo para CD de Especialização. O bônus inicial em dano se torna +4 e o aumento no dano por ponto de energia adicional gasto se torna +2.", 8, fighter, "Brutalidade"),
                        new ClassAbility("Feitiço e Punho", "Com precisão, você consegue agir rapidamente para utilizar do jujutsu e complementar com seu corpo. Uma vez por rodada, quando utilizar um Feitiço de dano com alvo único, você pode gastar 2PE para realizar um ataque corpo a corpo contra o mesmo alvo, desde que ele esteja dentro do seu alcance.", 8, fighter, "Mãos Amaldiçoadas"),
                        new ClassAbility("Golpear Brecha", "Você consegue aproveitar de um golpe aparado para atacar a brecha que se abre na defesa do inimigo. Quando utilizar Aparar Ataque e conseguir aparar com sucesso, você pode gastar 2PE adicionais para realizar um ataque contra o inimigo como parte da reação.", 8, fighter, "Aparar Ataque"),
                        new ClassAbility("Oportunista", "Você sabe aproveitar bem brechas na defesa dos inimigos. Uma vez por rodada, quando um inimigo dentro do seu alcance corpo a corpo é atingido por um ataque de uma criatura o flanqueando, você pode gastar 2 PE para fazer um ataque corpo a corpo contra a criatura.", 8, fighter),
                        new ClassAbility("Pancada Desnorteante", "Uma boa pancada deixa qualquer um despreparado para o que vem a seguir. Quando conseguir um acerto crítico em um ataque corpo a corpo, você pode fazer com que o alvo do ataque receba desvantagem contra um TR à sua escolha, até o início do seu próximo turno.", 8, fighter),
                        new ClassAbility("Punhos Letais", "Não há necessidade de armas se o seu corpo já é a mais letal entre elas. Enquanto estiver desarmado, sua margem de crítico diminui em 1 e seus ataques ignoram RD igual ao seu bônus de treinamento.", 8, fighter, "Poder Corporal"),

                        // Level 10 abilities
                        new ClassAbility("Alma Quieta", "Sua alma é imperturbável durante uma boa luta. Você recebe vantagem para resistir às seguintes condições: Condenado, Enfeitiçado e Fragilizado.", 10, fighter, "Treinado em Vontade"),
                        new ClassAbility("Corpo Sincronizado", "Seu corpo está sempre em sincronia. Você recebe vantagem para resistir às seguintes condições: Caído e Exposto.", 10, fighter, "Treinado em Fortitude"),
                        new ClassAbility("Empolgar-se", "Em certos momentos, a própria antecipação que você guarda para uma luta pode se transformar na empolgação necessária. Uma quantidade de vezes igual ao seu Bônus de treinamento, por descanso longo, você pode escolher subir dois níveis de empolgação, ao invés de um, no começo de um turno em que ele aumentaria.", 10, fighter),
                        new ClassAbility("Impacto Demolidor", "Você consegue colocar tanta força em um golpe que o alvo se torna uma bola de demolição. Como uma Ação Comum, realize uma jogada de ataque corpo a corpo contra um alvo dentro do seu alcance corpo a corpo e, caso acerte, você causa o dano do ataque e realiza a ação Empurrar como parte dele.", 10, fighter, "Potência Superior"),
                        new ClassAbility("Insistência", "Deixando o seu desejo se ampliar ainda mais, você se torna um lutador insistente e difícil de derrubar. Uma vez por cena, caso você fosse ter os seus pontos de vida reduzidos a 0, você pode escolher retornar ao nível de empolgação 1 para continuar de pé, curando-se em um valor igual a uma rolagem de dano do seu ataque desarmado.", 10, fighter, "Ignorar Dor"),
                        new ClassAbility("Mente em Paz", "Sua mente continua em paz mesmo durante o combate. Você recebe vantagem para resistir às seguintes condições Amedrontado, Atordoado e Confuso.", 10, fighter, "Treinado em Astúcia"),

                        // Level 12 abilities
                        new ClassAbility("Armas Absolutas", "Sua dominância com as Armas Dedicadas chega ao ápice, tornando-as uma parte íntegra de si mesmo. Enquanto estiver empunhando uma Arma Dedicada, você pode gastar 2PE para receber os seguintes bônus por uma rodada: você escolhe aumentar sua Defesa em 3 ou receber +3 em Jogadas de Ataque.", 12, fighter, "Um Com a Arma"),
                        new ClassAbility("Corpo Arsenal", "Você se torna plenamente consciente do complexo arsenal que o seu corpo é, podendo o utilizar ofensivamente de diferentes maneiras. Quando realizar um acerto crítico com um ataque desarmado, você pode optar por infligir o efeito de um grupo adicional entre Bastão, Haste ou Martelo.", 12, fighter, "Punhos Letais"),
                        new ClassAbility("Seja Água", "Não se colocando dentro de uma única forma, você aprende a se mover como a água, adaptando-se e não se prendendo. Seu Deslocamento aumenta em 3 metros, você ignora terreno difícil por fontes físicas (como detritos ou solo destruído) e, uma vez por rodada, pode evitar ser agarrado sem a necessidade de teste.", 12, fighter),
                        new ClassAbility("Tempestade Sufocante", "Seus golpes marciais são tão rápidos e potentes que se tornam uma tempestade que sufoca e destrói a guarda dos inimigos. Para cada ataque corpo a corpo desarmado ou com arma marcial que você acertar em um mesmo alvo, ele recebe -1 na Defesa e em Testes de Resistência realizados contra você.", 12, fighter),

                        // Level 16 abilities
                        new ClassAbility("Corpo Supremo", "Você alcançou um alto nível como lutador e levou seu corpo ao limite. Você recebe mais 3 metros de movimento adicionais, +4 na sua Defesa e redução de dano igual a metade do seu nível de personagem contra dano cortante, perfurante e de impacto.", 16, fighter),
                        new ClassAbility("Duro na Queda", "Quando estiver nas portas da morte, você pode escolher receber uma falha garantida para fazer um teste de Vontade contra a CD X, sendo X igual a 15 + 1 para cada 3 pontos de vida negativos. Se passar, você levanta com 1 de vida e recebe 1 ponto de exaustão.", 16, fighter, "Treinado em Vontade")
                ));

        List<ClassAbility> combatSpecialistAbilities = new ArrayList<>(List.of(
                // Level 2 abilities
                new ClassAbility("Arremessos Potentes", "Você se torna capaz de arremessar armas com mais potência. Seus ataques com armas de arremesso contam como um nível de dano acima. Além disso, no começo do seu turno, você pode gastar 1PE para fazer com que seus ataques com armas de arremesso ignorem RD igual ao seu bônus de treinamento.", 2, combatSpecialist),
                new ClassAbility("Arsenal Cíclico", "Ao invés de se limitar a uma única arma, você mantém uma ciclagem do seu arsenal para golpear com eficiência. Uma vez por rodada, você pode sacar ou trocar um item com uma ação livre. Ao realizar um golpe com um grupo de armas e trocar para outra arma de outro grupo na mesma rodada ou na próxima, você recebe +1d até o fim do seu próximo turno com a arma trocada.", 2, combatSpecialist),
                new ClassAbility("Assumir Postura", "A postura que uma pessoa mantém em combate molda suas capacidades, fornecendo grandes benefícios. Ao obter esta habilidade, você recebe acesso às posturas, explicadas e listadas no final da especialização.", 2, combatSpecialist),
                new ClassAbility("Disparos Sincronizados", "Você consegue sincronizar seus disparos e tiros, fazendo-os parecer um só. Caso esteja manejando duas armas a distância ou de fogo, você pode usar suas ações de ataque juntas para tentar sincronizar os dois tiros. Realize os dois ataques e, caso ambos acertem, você combina o dano em uma única instância.", 2, combatSpecialist),
                new ClassAbility("Erguer Guarda", "Mantendo uma guarda firme, você consegue se proteger de danos. Como uma Ação Bônus, você levanta a sua guarda: enquanto estiver com a guarda levantada, você recebe RD igual a metade do seu nível de especialista em combate somado ao seu bônus de treinamento.", 2, combatSpecialist),
                new ClassAbility("Escudeiro Agressivo", "Seu uso do escudo é não só defensivo, mas também agressivo. Uma vez por rodada, ao realizar uma ação de ataque e estiver empunhando um escudo, você pode gastar 1 ponto de energia amaldiçoada para fazer um ataque adicional com o escudo.", 2, combatSpecialist),
                new ClassAbility("Extensão do Corpo", "Suas armas são praticamente extensões do seu próprio corpo. Seu alcance em ataques com armas corpo a corpo aumenta em 1,5 metros e você recebe um bônus de +2 em jogadas de ataque e em testes para evitar ser desarmado.", 2, combatSpecialist),
                new ClassAbility("Flanqueador Superior", "Você sabe perfeitamente como manter um flanco perigoso. Enquanto estiver flanqueando uma criatura, a criatura flanqueada recebe -2 em testes de resistência.", 2, combatSpecialist),
                new ClassAbility("Golpe Falso", "Você é capaz de fingir desferir um golpe, distraindo seus inimigos para auxiliar aliados. Como reação a um aliado atacando um inimigo dentro do seu alcance de ataque, você realiza o golpe falso.", 2, combatSpecialist),
                new ClassAbility("Golpes Potentes", "Seus golpes se tornam inatamente mais potentes, sendo capaz de manejar armas extraindo seu máximo. Sempre que você estiver usando uma arma com a qual você se seja treinado o dano dela aumenta em um nível e suas rolagens de dano recebem um bônus de +2.", 2, combatSpecialist),
                new ClassAbility("Indomável", "Em combate, você não se deixa render, resistindo ao que vier. Uma quantidade de vezes por descanso curto ou longo igual a metade do seu nível de personagem, você pode gastar 1 ponto de energia amaldiçoada para rolar novamente um teste de resistência em que você falhar.", 2, combatSpecialist),
                new ClassAbility("Pistoleiro Iniciado", "Atirando com volatilidade, você consegue impor mais poder nas suas armas em troca de um risco maior. Quando for realizar um ataque com uma arma de fogo, antes da jogada de ataque, você pode escolher aumentar a margem de Emperrar em 2 e, em troca, você causa 1 dado de dano adicional caso acerte.", 2, combatSpecialist),
                new ClassAbility("Posicionamento Ameaçador", "Você sabe se posicionar de maneira estratégica, fazendo com que um inimigo que possa o ver te reconheça como uma constante ameaça, mesmo distante.", 2, combatSpecialist),
                new ClassAbility("Precisão Definitiva", "Você se torna capaz de canalizar a energia amaldiçoada na sua arma de maneira a alcançar uma precisão definitiva, seja para acertar ou para destruir.", 2, combatSpecialist),
                new ClassAbility("Presença Suprimida", "A furtividade e discrição podem ser essenciais em um combate, para se mover de maneira apropriada. Você recebe um bônus de +2 em rolagens de Furtividade.", 2, combatSpecialist),
                new ClassAbility("Revigorar", "Diante o quão extenso e cansativo um combate pode ser, você é capaz de focar e recuperar seu vigor.", 2, combatSpecialist),
                new ClassAbility("Tiro Falso", "Você consegue fingir falsos disparos, distraindo um inimigo. Como reação a um aliado atacando um inimigo dentro do seu alcance de ataque, caso esteja manejando uma arma a distância ou de fogo, você realiza um tiro falso.", 2, combatSpecialist),
                new ClassAbility("Zona de Risco", "Ter uma arma com o alcance maior o permite criar uma efetiva zona de risco. Uma vez por rodada, se estiver empunhando uma arma corpo-a-corpo com a propriedade Estendida e um inimigo entrar no seu alcance de ataque, você pode gastar 2 pontos de energia amaldiçoada para realizar um ataque contra ele.", 2, combatSpecialist),

                // Level 4 abilities
                new ClassAbility("Aprender Postura", "Você continua seu estudo sobre as posturas utilizadas em combate, expandindo seu repertório. Você aprende uma postura adicional à sua escolha.", 4, combatSpecialist, "Assumir Postura"),
                new ClassAbility("Armas Escolhidas", "Um tipo de arma ressoa de maneira única com você, e ela foi escolhida como seu caminho. Escolha um grupo de arma: seus ataques com armas dele tem o nível de dano aumentado em 3.", 4, combatSpecialist),
                new ClassAbility("Arremesso Rápido", "Utilizando de armas leves e menores, você consegue as arremessar com velocidade. Uma vez por rodada, ao realizar um ataque com uma arma de arremesso, você pode gastar 1PE para realizar um ataque com arma de arremesso contra outro alvo.", 4, combatSpecialist),
                new ClassAbility("Técnicas de Avanço", "As técnicas de avanço envolvem a mistura do deslocamento com os golpes. Ao obter esta habilidade, você pode aprender uma das duas artes de combate de avanço.", 4, combatSpecialist),
                new ClassAbility("Buscar Oportunidade", "Você sabe como encontrar a oportunidade certa para fazer o que é necessário. Como uma Ação Livre, realize um teste de Percepção com CD16 + 2 para cada inimigo em campo.", 4, combatSpecialist),
                new ClassAbility("Compensar Erro", "Você se torna habilidoso o suficiente para compensar erros com a liberação bruta de energia. Uma vez por rodada, quando errar um ataque com uma arma corpo a corpo, você pode gastar até uma quantidade de PE igual ao seu bônus de treinamento para causar dano no alvo do ataque.", 4, combatSpecialist),
                new ClassAbility("Especialista em Escudo", "Você se especializa completamente na defesa e no uso de escudos. Você passa a somar o aumento base em RD do seu escudo em testes de resistência de Reflexos e Fortitude.", 4, combatSpecialist),
                new ClassAbility("Espírito de Luta", "O combate é um caminho, no qual você nutre um espírito intenso para lutar. Como uma Ação Livre, você pode gastar 1PE para receber um bônus de +2 em jogadas de ataque até o fim da cena.", 4, combatSpecialist),
                new ClassAbility("Grupo Favorito", "Você descobre como utilizar melhor um certo tipo de armas. Escolha um grupo de armas: você recebe acesso ao efeito de crítico do grupo enquanto manejando uma arma que pertença a ele.", 4, combatSpecialist),
                new ClassAbility("Guarda Estudada", "Sua guarda surge a partir do estudo e da reflexão. Você passa a somar metade do seu modificador de Sabedoria na sua Defesa, limitado pelo seu nível.", 4, combatSpecialist),
                new ClassAbility("Mente Ocultada", "Você treinou sua mente para se ocultar, aguçando-a para encontrar os lugares certos. Você passa a adicionar também o seu bônus de sabedoria em rolagens de Furtividade.", 4, combatSpecialist),
                new ClassAbility("Preparo Imediato", "Utilizando do seu preparo, você consegue rapidamente se colocar pronto para agir. Durante uma rolagem de iniciativa, você pode gastar 3 pontos de preparo para utilizar Preparar, mas apenas para uma ação bônus.", 4, combatSpecialist),
                new ClassAbility("Recarga Rápida", "Você se treinou e preparou para conseguir recarregar rapidamente. O custo em ações para recarregar armas a distância que você empunhar diminui em um nível.", 4, combatSpecialist),
                new ClassAbility("Uso Rápido", "Para ter mais versatilidade e acessibilidade ao seu inventário de ferramentas, você agiliza o uso delas. Ao utilizar uma ação para usar um item, você pode pagar 1 ponto de energia para usar um item adicional.", 4, combatSpecialist),

                // Level 6 abilities
                new ClassAbility("Acervo Ample", "Seu acervo para o combate é amplo, conseguindo internalizar e manifestar qualquer estilo que desejar. Ao obter esta habilidade, você aprende mais um Estilo de Combate.", 6, combatSpecialist),
                new ClassAbility("Aprimoramento Especializado", "Você aprimora suas habilidades de combate para deixar mais difícil resistir as suas técnicas de Especialista em Combate. Você passa a somar metade do modificador do seu atributo chave em sua CD de Especialização.", 6, combatSpecialist),
                new ClassAbility("Ataque Extra", "Você consegue atacar mais rápido, otimizando seus golpes. Ao realizar a ação Atacar, você pode gastar 2 PE para atacar duas vezes ao invés de uma.", 6, combatSpecialist),
                new ClassAbility("Crítico Melhorado", "Você aguça o seu olhar para tornar mais fácil encaixar um golpe certeiro. A margem do seu acerto crítico reduz em um número.", 6, combatSpecialist),
                new ClassAbility("Crítico Potente", "Acertar um golpe certeiro é realmente devastador para você. Ao acertar um ataque crítico, ele causa 1 dado de dano adicional.", 6, combatSpecialist),
                new ClassAbility("Defender e Revidar", "Além de defender, você consegue revidar um ataque. Estando com a guarda erguida, ao receber um ataque, você pode realizar um ataque contra o atacante como uma reação.", 6, combatSpecialist, "Erguer Guarda"),
                new ClassAbility("Feitiçaria Implementada", "O jujutsu é um recurso indispensável, o qual você implementa no seu combate. Uma vez por rodada, quando utilizar um Feitiço de dano, você pode gastar 2PE para realizar um ataque contra uma criatura que tenha sido afetada por ela.", 6, combatSpecialist, "Treinado em Feitiçaria"),
                new ClassAbility("Fluxo Perfeito", "Em certos momentos, o fluxo do combate é perfeito em sua mente. Caso você acerte todos os seus ataques no turno, no seu próximo turno você ganha 1 ponto de energia amaldiçoada temporária.", 6, combatSpecialist),
                new ClassAbility("Olhos de Águia", "Seu olhar é afiado e preciso como o de uma águia, permitindo-o mirar mais rapidamente. Você pode gastar 1 PE para usar Mirar como uma ação livre.", 6, combatSpecialist),
                new ClassAbility("Manejo Especial", "A maneira a qual você maneja suas armas é única, feita com maestria inerente ao portador. Você pode escolher uma propriedade de ferramenta amaldiçoada para ser aplicada em toda arma que você estiver manejando.", 6, combatSpecialist),
                new ClassAbility("Marcar Inimigo", "Após um golpe, você marca um inimigo como seu no campo de batalha, impedindo-o de atacar e retaliando tentativas de o ignorar.", 6, combatSpecialist),
                new ClassAbility("Mira Destrutiva", "Ao invés de apenas acertar, você é capaz de mirar para destruir completamente, em um disparo difícil, mas recompensador.", 6, combatSpecialist, "Treinado em Percepção"),
                new ClassAbility("Preparação Rápida", "A arte das posturas já está encravada em sua mente, tornando-se algo rápido e imediato. Entrar em uma postura se torna uma Ação Livre e elas não são canceladas caso você seja empurrado.", 6, combatSpecialist, "Assumir Postura"),

                // Level 8 abilities
                new ClassAbility("Aptidões de Combate", "Você aprimora suas aptidões de energia necessárias para dominar o combate. Ao obter esta habilidade, você pode aumentar o seu nível de aptidão em Aura ou Controle e Leitura em 1.", 8, combatSpecialist),
                new ClassAbility("Técnicas da Força", "As técnicas da força permitem uma concentração ainda maior da sua potência e poder. Ao obter esta habilidade, você pode aprender uma das duas artes de combate da força.", 8, combatSpecialist),
                new ClassAbility("Destruição Dupla", "Duas armas em mãos, o dobro de destruição para seus inimigos. Enquanto estiver lutando com duas armas de grupos diferentes, seu ataque com a segunda arma causa 1 dado de dano adicional.", 8, combatSpecialist),
                new ClassAbility("Espírito Incansável", "Nada pode abalar o seu espírito para lutar, o qual se torna ainda mais persistente. Quando utilizar Espírito de Luta, você pode optar por gastar 2PE ao invés de 1.", 8, combatSpecialist, "Espírito de Luta"),
                new ClassAbility("Pistoleiro Avançado", "Suas técnicas como pistoleiro se tornam ainda mais afiadas, conseguindo tomar riscos maiores e encontrar novas oportunidades com as armas.", 8, combatSpecialist, "Pistoleiro Iniciado"),
                new ClassAbility("Ricochete Constante", "Imbuídas com energia, suas armas de arremesso colidem e explodem em energia, ricocheteando para um próximo alvo.", 8, combatSpecialist),
                new ClassAbility("Sombra Viva", "Você é como uma sombra, movendo-se rapidamente e de maneira imperceptível. Uma vez por rodada, você pode utilizar Esgueirar e se mover todo o seu movimento, ao invés de apenas metade.", 8, combatSpecialist, "Treinado em Furtividade"),
                new ClassAbility("Surto de Ação", "Em momentos cruciais, você consegue se forçar a agir mais, excedendo suas capacidades normais.", 8, combatSpecialist),

                // Level 10 abilities
                new ClassAbility("Análise Acelerada", "Você já se acostumou a analisar o campo de batalha como um reflexo ou instinto. Utilizar a ação de Análise se torna uma ação bônus.", 10, combatSpecialist),
                new ClassAbility("Armas Perfeitas", "Suas armas escolhidas se tornaram perfeitas, sabendo como contornar fraquezas e defesas. Seus ataques com uma arma do grupo escolhido em Armas Escolhidas ignoram resistência ao tipo de dano dela.", 10, combatSpecialist, "Armas Escolhidas"),
                new ClassAbility("Assassinar", "Durante o primeiro momento, você é capaz de extrair letalidade absoluta, golpeando um inimigo desprevenido com um bote poderoso.", 10, combatSpecialist, "Mestre em Furtividade"),
                new ClassAbility("Ataque Concentrado", "Ao invés de desferir vários golpes, você concentra tudo em um único brandir.", 10, combatSpecialist, "Ataque Extra"),
                new ClassAbility("Chuva de Arremessos", "Você consegue extrair rapidez dos seus arremessos, realizando-os em um ritmo absurdo e influenciado pela energia.", 10, combatSpecialist, "Arremessos Potentes e Arremesso Rápido"),
                new ClassAbility("Potência Antes de Cair", "Ao reconhecer que em breve você irá cair, você consegue impactar grandemente o combate antes dessa queda.", 10, combatSpecialist),

                // Level 12 abilities
                new ClassAbility("Técnicas de Saque", "As técnicas de saque permitem que o próprio ato de sacar uma arma se torna destrutivo. Ao obter esta habilidade, você pode aprender uma das duas artes de combate de saque.", 12, combatSpecialist),
                new ClassAbility("Ciclagem Absoluta", "O ciclo mantido entre seu arsenal é absoluto, conectando armas diferentes com facilidade.", 12, combatSpecialist, "Arsenal Cíclico"),
                new ClassAbility("Manejo Único", "Desenvolvendo ainda mais no seu próprio manejo de armas, você alcança um nível especial.", 12, combatSpecialist, "Manejo Especial"),
                new ClassAbility("Mestre Pistoleiro", "Em suas mãos, as armas podem extrair todo o seu potencial, agora sendo as ferramentas de um mestre.", 12, combatSpecialist, "Pistoleiro Avançado"),
                new ClassAbility("Sincronia Perfeita", "Você está em perfeita sincronia com suas armas, as quais se tornam uma parte do seu corpo, deixando-o ainda mais livre.", 12, combatSpecialist, "Extensão do Corpo"),

                // Level 16 abilities
                new ClassAbility("Crítico Aperfeiçoado", "Seu senso de combate se torna ainda mais afiado e letal, encaixando críticos com maior facilidade.", 16, combatSpecialist, "Crítico Melhorado"),
                new ClassAbility("Mestre da Postura", "Você se torna um mestre completo das posturas, dominando-as de uma maneira que poucos são capazes, até mesmo as mesclando.", 16, combatSpecialist, "Assumir Postura")
        ));

        List<ClassAbility> techniqueSpecialistAbilities = new ArrayList<>(List.of(
                // Level 2 abilities
                new ClassAbility("Abastecido pelo Sangue", "O sangue de seus inimigos também é capaz de o abastecer, trazendo mais energia amaldiçoada. Quando um inimigo morre dentro de 12 metros de você, você pode usar sua reação para recuperar uma quantidade de energia amaldiçoada igual ao seu modificador de Inteligência ou Sabedoria.", 2, techniqueSpecialist),
                new ClassAbility("Conhecimento Aplicado", "Sendo um especialista em técnicas, você as conhece muito bem e consegue aplicar esse conhecimento de maneira defensiva contra outros usuários de técnica. Sempre que for realizar um teste de resistência contra o efeito de um Feitiço, você pode gastar pontos de energia amaldiçoada igual a metade do seu bônus de treinamento para receber um bônus.", 2, techniqueSpecialist),
                new ClassAbility("Conjuração Defensiva", "Após uma conjuração, você mantém parte da energia amaldiçoada como um revestimento em seu corpo. Ao usar um Feitiço, você pode gastar 2 PE para, até o começo do seu próximo turno, receber um bônus em Defesa e um valor em RD igual ao nível do Feitiço usado.", 2, techniqueSpecialist),
                new ClassAbility("Economia de Energia", "Enquanto descansando você armazena parte de sua energia em uma economia reserva. Após um descanso curto, sua reserva é igual a 1d4, após um descanso longo esse valor é 1d6, aumentando em um dado a cada 5 níveis.", 2, techniqueSpecialist),
                new ClassAbility("Explosão Encadeada", "Um bom desempenho em uma conjuração o permite aumentar o poder destrutivo, encadeando a força. Ao rolar o dano máximo em um dado de dano de um Feitiço de dano, você rola mais um dado de dano de mesmo valor.", 2, techniqueSpecialist),
                new ClassAbility("Finta Amaldiçoada", "Você é capaz de enganar com falsas conjurações de técnica. Você pode utilizar Fintar com seu atributo-chave ao invés de Presença e os efeitos de Desprevenido por fintar são aplicados na sua próxima conjuração de Feitiço.", 2, techniqueSpecialist),
                new ClassAbility("Mente Plácida", "Sua mente é sempre plácida, dificultando que sua concentração seja quebrada. Quando realizar um teste para manter concentração, você pode gastar 1 ponto de energia para receber um bônus de +3 ou 2 pontos de energia para receber +5.", 2, techniqueSpecialist),
                new ClassAbility("Nova Habilidade", "Uma nova ideia surge em sua mente, a qual você transforma em uma habilidade inédita. Ao obter esta habilidade, você pode imediatamente criar dois novos Feitiços ou três variações de liberação.", 2, techniqueSpecialist),
                new ClassAbility("Perturbação Amaldiçoada", "Energia amaldiçoada é energia negativa, e você consegue extrair essa negatividade e a impor em um inimigo, prejudicando o seu desempenho.", 2, techniqueSpecialist),
                new ClassAbility("Reação Rápida", "Você sempre reage rápido quando uma situação de combate começa. Você passa a adicionar seu modificador de Inteligência ou Sabedoria no seu bônus de iniciativa.", 2, techniqueSpecialist),
                new ClassAbility("Reforço Amaldiçoado", "Você reforça as suas habilidades, tornando mais difícil resistir a elas. Sua CD de Especialização e Amaldiçoada aumenta em +2.", 2, techniqueSpecialist),
                new ClassAbility("Sobrecarregar", "Focando em sobrecarregar as suas habilidades, você pode consumir energia para a deixar quase impossível de resistir.", 2, techniqueSpecialist),
                new ClassAbility("Técnicas de Combate", "Você decide se versar em técnicas essenciais de combate, em busca de conseguir se defender em casos extremos.", 2, techniqueSpecialist),
                new ClassAbility("Zelo Recompensador", "O seu zelo diante si mesmo é recompensador: sempre que você suceder em um teste de resistência para evitar o efeito de um Feitiço, você recebe 1 ponto de energia amaldiçoada temporário.", 2, techniqueSpecialist),

                // Level 4 abilities
                new ClassAbility("Até a Última Gota", "Você vai sempre utilizar até a última gota de energia amaldiçoada que houver em seu corpo. Uma vez por descanso longo, caso esteja com menos da metade do seu máximo de energia amaldiçoada, você pode usar uma ação comum para recuperar 1d4 + seu modificador de Int/Sab em pontos de energia.", 4, techniqueSpecialist),
                new ClassAbility("Ciclagem Maldita", "Alternar entre suas habilidades permite que você encaixe cada uma de maneira diferente, beneficiando a ciclagem. Quando utilizar um Feitiço de dano diferente do último Feitiço que você utilizou anteriormente, ele causa uma quantidade de dados de dano adicionais igual a metade do seu bônus de treinamento.", 4, techniqueSpecialist),
                new ClassAbility("Determinação Energizada", "A partir da energia, você consegue criar uma determinação superior para a sua mente, acelerando-a ou reforçando-a.", 4, techniqueSpecialist),
                new ClassAbility("Energia Focalizada", "Você foca a sua energia em algum aspecto do seu corpo, assim potencializando alguma resistência sua.", 4, techniqueSpecialist),
                new ClassAbility("Energia Inacabável", "Você aumenta ainda mais a quantidade de energia amaldiçoada que você possui. Seu máximo de energia amaldiçoada aumenta em um valor igual a metade do seu nível de Especialista em Técnicas.", 4, techniqueSpecialist),
                new ClassAbility("Epifania Amaldiçoada", "Ao desvendar mais da energia amaldiçoada, você obtém uma nova capacidade envolvendo-a. Ao obter essa habilidade, você aprende uma Aptidão Amaldiçoada.", 4, techniqueSpecialist),
                new ClassAbility("Explosão Defensiva", "Reagindo a um ataque com uma grande explosão de energia amaldiçoada, você consegue reduzir os danos dele.", 4, techniqueSpecialist, "Aptidão Cobrir-se"),
                new ClassAbility("Feitiço Favorito", "Um dos seus Feitiços é o seu favorito, sendo levado para um nível superior de maneira natural.", 4, techniqueSpecialist),
                new ClassAbility("Feitiços Refinados", "Seus Feitiços como um todo são refinados pelo seu controle de energia, sendo mais difícil resistir a eles.", 4, techniqueSpecialist),
                new ClassAbility("Movimentos Imprevisíveis", "Você aprende a se mover de maneira imprevisível, dificultando tentativas de ataque contra você.", 4, techniqueSpecialist),
                new ClassAbility("Naturalidade com Rituais", "Realizar rituais se torna algo mais natural para sua mente, permitindo-o colocar o raciocínio acima da agilidade.", 4, techniqueSpecialist, "Treinado em Prestidigitação"),
                new ClassAbility("Preparação de Técnicas", "Você consegue preparar habilidades para assim economizar energia ao usá-las.", 4, techniqueSpecialist),
                new ClassAbility("Olhar Preciso", "Sua visão é precisa e, consequentemente, sua mira também. Você recebe um bônus de +2 em rolagens de ataque para Feitiços e aptidões amaldiçoadas.", 4, techniqueSpecialist),
                new ClassAbility("Sacrifício pela Energia", "Você é capaz de até mesmo sacrificar a sua própria vida para conseguir mais energia amaldiçoada, em casos de urgência.", 4, techniqueSpecialist),
                new ClassAbility("Versatilidade em Fundamentos", "Além de dominar, você também é versátil no que se diz os fundamentos das técnicas.", 4, techniqueSpecialist),

                // Level 6 abilities
                new ClassAbility("Bastião Interior", "Com uma mente convicta e resistente, você transforma seu interior em um bastião. Você recebe vantagem para resistir às condições amedrontado, desorientado e enfeitiçado.", 6, techniqueSpecialist, "Treinado em Vontade"),
                new ClassAbility("Combate Amaldiçoado", "Ampliando no uso de armas corpo-a-corpo, você assume um estilo de combate amaldiçoado que a incorpora no uso da sua energia.", 6, techniqueSpecialist, "Técnicas de Combate"),
                new ClassAbility("Correção", "Você consegue se corrigir caso esteja para perder o foco. Uma vez por rodada, quando você for perder a concentração em um Feitiço, você pode gastar pontos de energia amaldiçoada igual ao nível do Feitiço para evitar perder a concentração nele.", 6, techniqueSpecialist),
                new ClassAbility("Dominância em Feitiço", "Você usa tanto um Feitiço da sua técnica que você passa a dominar ele completamente e otimizar seu uso ao máximo.", 6, techniqueSpecialist),
                new ClassAbility("Elevar Aptidão", "Como um mestre em técnicas jujutsu no geral, você eleva seu nível em uma das aptidões.", 6, techniqueSpecialist),
                new ClassAbility("Especialização", "Você aprimora seus conhecimentos, tornando-se exímio em certas perícias.", 6, techniqueSpecialist),
                new ClassAbility("Incapaz de Falhar", "Sua maestria sobre as aptidões torna mais difícil falhar.", 6, techniqueSpecialist),
                new ClassAbility("Mente Repartida", "Você é capaz de repartir sua mente em duas seções. Você pode se manter concentrando em duas fontes diferentes simultaneamente.", 6, techniqueSpecialist),
                new ClassAbility("Nível Perfeito", "Você escolhe um nicho de feitiços para ser aprimorada. Todos os seus Feitiços de um nível a sua escolha têm a CD de resistência aumentada em 2.", 6, techniqueSpecialist),
                new ClassAbility("Passo Rápido", "Você se move agilmente, preparado para se afastar caso necessário.", 6, techniqueSpecialist),
                new ClassAbility("Potência Concentrada", "Quando for disparar uma manifestação de sua técnica, você é capaz de se preparar e concentrar para aumentar o poder.", 6, techniqueSpecialist),
                new ClassAbility("Ritualista", "Você é familiar com a aplicação de rituais em suas conjurações, conseguindo ampliar a capacidade deles.", 6, techniqueSpecialist),

                // Level 8 abilities
                new ClassAbility("Expansão dos Fundamentos", "Você expande seu domínio sobre os fundamentos, versando-se em novas maneiras de modificar as técnicas.", 8, techniqueSpecialist),
                new ClassAbility("Físico Amaldiçoado Defensivo", "Reconhecendo o potencial da energia amaldiçoada para o proteger, você foca nessas aplicações, tornando-se mais capaz de resistir.", 8, techniqueSpecialist, "Aptidão Cobrir-se"),
                new ClassAbility("Imbuir com Técnica", "Você se torna capaz de imbuir armas com a sua própria técnica, potencializando-as grandemente.", 8, techniqueSpecialist, "Combate Amaldiçoado"),
                new ClassAbility("Liberações Expandidas", "Você encontra maneiras de ter um repertório de liberações máximas maior.", 8, techniqueSpecialist),
                new ClassAbility("Mira Aperfeiçoada", "Sua mira para feitiços é mais afiada, permitindo-o acertar com maior precisão diante preparo.", 8, techniqueSpecialist, "Olhar Preciso"),
                new ClassAbility("Primeiro Disparo", "Quando um combate se inicia, você é o primeiro a disparar.", 8, techniqueSpecialist, "Treinado em Reflexos"),
                new ClassAbility("Revestimento Constante", "Seu corpo está constantemente revestido com a sua energia amaldiçoada.", 8, techniqueSpecialist, "Aptidão Cobrir-se"),
                new ClassAbility("Sustentação Avançada", "Seu corpo agora é capaz de dividir a liberação de energia entre dois feitiços diferentes.", 8, techniqueSpecialist),

                // Level 10 abilities
                new ClassAbility("Destruição Ampla", "Quanto mais você conseguir abranger em sua conjuração, mais você é capaz de destruir.", 10, techniqueSpecialist),
                new ClassAbility("Destruição Focada", "Ao invés de espalhar a destruição, você a foca em um único ponto ou criatura.", 10, techniqueSpecialist),
                new ClassAbility("Economia de Energia Avançada", "Sua economia reserva se torna ainda maior, expandindo seu estoque.", 10, techniqueSpecialist, "Economia de Energia"),
                new ClassAbility("Sentidos Aguçados", "O domínio sobre a energia aguça seus sentidos ao limite, transformando-o em alguém que não deixa nenhum detalhe escapar.", 10, techniqueSpecialist, "Mestre em Percepção"),

                // Level 12 abilities
                new ClassAbility("Esgrimista Jujutsu", "Mesclando técnicas de combate e feitiçaria ao máximo, você se torna digno de ser visto como um esgrimista jujutsu.", 12, techniqueSpecialist, "Combate Amaldiçoado"),
                new ClassAbility("Expansão Maestral", "Você pode utilizar expansões de domínio possuindo apenas uma mão livre e ataques a distância não causam ataques de oportunidade contra você enquanto expandindo.", 12, techniqueSpecialist, "Aptidão Expansão de Domínio Completa"),
                new ClassAbility("Explosão Máxima", "O potencial de aumento para o poder destrutivo de suas técnicas é ainda maior, levando-o ao máximo.", 12, techniqueSpecialist, "Explosão Encadeada"),
                new ClassAbility("Mestre das Aptidões", "Você é um mestre no uso das aptidões amaldiçoadas, conseguindo reservar um pouco do seu potencial para elas.", 12, techniqueSpecialist),
                new ClassAbility("Versatilidade Ampliada", "Ser versátil no uso dos próprios feitiços é uma grande vantagem, e você decide investir nela.", 12, techniqueSpecialist),

                // Level 16 abilities
                new ClassAbility("Manipulação Perfeita", "Seu conhecimento sobre a manipulação de energia é melhorado, permitindo que você escolha uma quantidade de Feitiços igual ao seu bônus de treinamento para terem seu custo reduzido.", 16, techniqueSpecialist, "Dominância em Habilidade"),
                new ClassAbility("Sustentação Mestre", "Com o passar do tempo, você descobriu novas formas de como dispersar energia pelo seu corpo, conseguindo sustentar mais feitiços e com maior eficiência.", 16, techniqueSpecialist, "Sustentação Avançada")
        ));

        List<ClassAbility> controllerAbilities = new ArrayList<>(List.of(
                // Level 2 abilities
                new ClassAbility("Aceleração", "Estimulando-as com seus comandos, você é capaz de forçar uma aceleração maior em invocações. Uma vez por rodada, você pode fazer com que uma Invocação se mova duas vezes ao invés de uma.", 2, controller),
                new ClassAbility("Camuflagem Aprimorada", "Você consegue se mesclar no meio das suas invocações, camuflando-se. Você pode, como uma Ação Comum, camuflar-se em meio as suas invocações adjacentes a você.", 2, controller),
                new ClassAbility("Chamado Destruidor", "Um acerto preciso de uma invocação incentiva as outras a acompanhar, como um chamado destrutivo. Quando uma das suas invocações conseguir um acerto crítico em uma ação de ataque você pode, como uma Ação Livre, pagar 2 PE para fazer com que uma das suas invocações adjacentes ataque o mesmo alvo.", 2, controller),
                new ClassAbility("Companheiro Amaldiçoado", "Uma das suas invocações se torna seu companheiro, tornando-se mais capaz de ajudar. Escolha uma invocação sua: ela se torna o seu companheiro amaldiçoado.", 2, controller),
                new ClassAbility("Dor Partilhada", "Você e uma invocação conseguem criar um laço para partilhar dor, e isso pode acabar amenizando-a.", 2, controller),
                new ClassAbility("Frenesi da Invocação", "Você consegue fazer com que suas invocações se rendam a um frenesi brutal, mas arriscado.", 2, controller),
                new ClassAbility("Guarda Viva", "Suas invocações atuam como uma guarda viva para você, auxiliando em sua defesa.", 2, controller),
                new ClassAbility("Invocações Móveis", "Você prepara suas invocações para se moverem com mais velocidade. O Deslocamento de todas suas Invocações aumenta em 1,5 metros.", 2, controller),
                new ClassAbility("Invocações Resistentes", "Você torna suas invocações mais resistentes, amplificando a vitalidade delas.", 2, controller),
                new ClassAbility("Invocações Treinadas", "Você faz com que suas invocações sejam mais aptas em habilidades.", 2, controller),
                new ClassAbility("Melhoria de Controlador", "Estudando novas táticas e especializando-se em aspectos específicos, você aplica certas melhorias em todas suas invocações.", 2, controller),
                new ClassAbility("Otimização de Energia", "Você consegue otimizar o gasto de energia das habilidades mais exaustivas das suas invocações.", 2, controller),
                new ClassAbility("Proteger Invocação", "Você sabe do valor das suas invocações, podendo até mesmo utilizar delas para sacrifícios em prol de si mesmas.", 2, controller),
                new ClassAbility("Rede de Detecção", "Juntamente das suas invocações, você se atenta e é auxiliado por elas para não perder nenhum detalhe.", 2, controller),
                new ClassAbility("Técnicas de Combate", "Você decide se versar em técnicas essenciais de combate, em busca de conseguir se defender em casos extremos.", 2, controller),
                new ClassAbility("Visionário", "Você expande sua visão para a criação de invocações, conseguindo as conferir mais aspectos únicos.", 2, controller),

                // Level 4 abilities
                new ClassAbility("Ação Corretiva", "Sempre atento ao campo de batalha, você consegue corrigir falhas de suas invocações.", 4, controller),
                new ClassAbility("Acompanhamento Amaldiçoado", "Uma das suas invocações pode ser colocada para o acompanhar de perto, reagindo aos seus golpes.", 4, controller),
                new ClassAbility("Ataque em Conjunto", "Você consegue unificar seus comandos para fazer com que suas invocações ataquem em conjunto.", 4, controller),
                new ClassAbility("Autonomia", "Assumindo uma abordagem diferente, você traz uma invocação a campo com autonomia, deixando-a agir de maneira independente.", 4, controller),
                new ClassAbility("Companheiro Avançado", "O seu companheiro amaldiçoado se torna ainda mais avançado, conseguindo se versar em mais uma função, que é a de um aliado.", 4, controller, "Companheiro Amaldiçoado"),
                new ClassAbility("Crítico Brutal", "A brutalidade de um golpe bem encaixado por uma invocação é ampliada.", 4, controller),
                new ClassAbility("Domador de Maldições", "Você se prepara para ser capaz de domar maldições com efetividade superior.", 4, controller),
                new ClassAbility("Invocação Ás", "Seu companheiro amaldiçoado se torna também a sua invocação ás, capaz de o ajudar grandemente.", 4, controller, "Companheiro Amaldiçoado"),
                new ClassAbility("Invocação Parcial", "Nem sempre é necessário trazer uma invocação por completo para se beneficiar de suas capacidades.", 4, controller),
                new ClassAbility("Potencial Superior", "Suas invocações possuem um potencial superior para desenvolver seus atributos.", 4, controller),

                // Level 6 abilities
                new ClassAbility("Combate em Alcateia", "Você se torna parte da própria alcateia das suas invocações, golpeando com mais poder enquanto cercado delas.", 6, controller, "Técnicas de Combate e Apogeu - Controle Sintonizado"),
                new ClassAbility("Concentrar Poder", "Priorizando qualidade acima de quantidade, você consegue concentrar o poder em uma única invocação.", 6, controller, "Apogeu - Controle Concentrado"),
                new ClassAbility("Hoste Amaldiçoada", "Você se foca em formar um exército de baixo nível.", 6, controller, "Apogeu - Controle Disperso"),
                new ClassAbility("Invocações Econômicas", "Trazer algumas das suas invocações para o combate se torna mais econômico, permitindo-o trazê-las mais frequentemente quando retiradas.", 6, controller),
                new ClassAbility("Proteção Avançada de Invocação", "Aprofundando-se ainda mais em técnicas defensivas para suas invocações, você se torna mais capaz.", 6, controller, "Proteger Invocação"),
                new ClassAbility("Táticas de Alcateia", "Caso tenha uma criatura agressiva sendo flanqueada por uma das suas invocações, a Defesa dela diminui.", 6, controller),

                // Level 8 abilities
                new ClassAbility("Aptidões de Controle", "Você aprimora suas aptidões de energia necessárias para ser um mestre controlador.", 8, controller),
                new ClassAbility("Atacar e Invocar", "Priorizando um combate próximo e em meio as suas invocações, você consegue trazê-las junto de um golpe.", 8, controller),
                new ClassAbility("Golpes Ágeis", "Seus ataques se tornam mais ágeis, visando permitir comandar as invocações e ainda assim atacar por si só.", 8, controller, "Acompanhamento Amaldiçoado"),
                new ClassAbility("Técnicas de Oportunidade", "Suas invocações se tornam aptas a novas técnicas de combate, encontrando boas oportunidades.", 8, controller),

                // Level 10 abilities
                new ClassAbility("Buchas de Canhão", "Invocações de menor grau não possuem muito valor sozinhas, mas são ótimas para compor uma horda.", 10, controller),
                new ClassAbility("Crítico Aprimorado", "Um 19 se torna crítico também para suas invocações.", 10, controller, "Crítico Brutal"),
                new ClassAbility("Flanco Avançado", "Você aprimora as técnicas de flanco das suas invocações, transformando-as em obstáculos ainda maiores para os inimigos.", 10, controller, "Táticas de Alcateia"),
                new ClassAbility("Resistência Sobrecarregada", "Você pode sobrecarregar a resistência das suas invocações a partir da sua própria energia amaldiçoada.", 10, controller, "Invocações Resistentes"),

                // Level 16 abilities
                new ClassAbility("Fantoche Supremo", "Durante um descanso, você é capaz de reforçar o poderio de uma invocação que pareça que será essencial.", 16, controller),
                new ClassAbility("Mestre do Controle", "Você se torna um mestre do controle, levando suas técnicas ao limite.", 16, controller)
        ));

        List<ClassAbility> supportAbilities = new ArrayList<>(List.of(
                // Level 2 abilities
                new ClassAbility("Amizade Inquebrável", "Escolha um Aliado Jogador. Este aliado é considerado permanentemente seu 'Amigo'. Ao terminar seu turno ao lado de seu Amigo, você pode como ação livre realizar a Ação 'Apoiar' no mesmo.", 2, support),
                new ClassAbility("Análise Profunda", "Você consegue analisar profundamente um inimigo, deduzindo aspectos dele.", 2, support),
                new ClassAbility("Apoio Avançado", "Ao utilizar a ação de Apoiar, você pode fortalecer seu apoio com um efeito à sua escolha.", 2, support),
                new ClassAbility("Conceder Outra Chance", "Você pode conceder a um aliado outra chance em um teste no qual ele falhou.", 2, support),
                new ClassAbility("Comando Motivador", "Sua presença é motivadora, e o mesmo vale para um comando dado por você.", 2, support),
                new ClassAbility("Desvendar Terreno", "Você consegue compreender e destrinchar o ambiente ao seu redor, encontrando pontos de vantagem no terreno.", 2, support),
                new ClassAbility("Expandir Repertório", "Estudando para se tornar mais versátil, você consegue dominar outros campos de estudos.", 2, support),
                new ClassAbility("Mobilidade Avançada", "Em prol de alcançar mais rapidamente o lugar onde seu suporte é requisitado você recebe um bônus de +3 metros em seu movimento.", 2, support),
                new ClassAbility("Otimização de Espaço", "Você organiza melhor o seu inventário e o seu espaço.", 2, support),
                new ClassAbility("Pronto para Agir", "Você adiciona seu modificador de Presença a Iniciativa. Além disso, seus aliados recebem um bônus igual a metade do modificador.", 2, support),
                new ClassAbility("Protetor", "Quando um aliado dentro de 1,5m de você é atacado, você pode gastar 2 PE para, como uma Ação Livre, diminuir o dano causado no ataque feito contra ele.", 2, support),
                new ClassAbility("Técnicas de Combate", "Você decide se versar em técnicas essenciais de combate, em busca de conseguir se defender em casos extremos.", 2, support),
                new ClassAbility("Transmitir Conhecimento", "Durante um descanso, você pode transmitir conhecimento para seus aliados, preparando-os.", 2, support),

                // Level 4 abilities
                new ClassAbility("Apoios Versáteis", "Ao obter esta habilidade, você aprende um apoio avançado adicional.", 4, support),
                new ClassAbility("Guarda Sincronizada", "Um cuida do outro e, mantendo essa mentalidade, você consegue estabelecer uma guarda em sintonia com seus aliados próximos.", 4, support),
                new ClassAbility("Inspirar Aliados", "Você sabe como dar a inspiração necessária para os seus aliados.", 4, support),
                new ClassAbility("Intervenção", "Você é capaz de intervir na situação de um aliado, assim podendo impedir condições.", 4, support),
                new ClassAbility("Negação Crítica", "Você é capaz de negar uma falha crítica dos seus aliados, impedindo o pior de acontecer.", 4, support),
                new ClassAbility("No Último Segundo", "Ao iniciar uma rodada com um ou mais aliados com 2 fracassos nos testes da porta da morte, aumente sua iniciativa atual em combate em +5.", 4, support),
                new ClassAbility("Pré-Análise", "Você inconscientemente analisa o território a sua volta, sendo assim você não pode ser surpreendido e seu valor de atenção recebe um bônus de +5.", 4, support, "Treinado em Percepção"),
                new ClassAbility("Recompensa pelo Sucesso", "Você recompensa aqueles que você comanda, com um sucesso mais difícil sendo extremamente gratificante.", 4, support, "Comando Motivador"),
                new ClassAbility("Sintonização Vital", "Quando curar um aliado, você pode gastar 3 pontos de energia amaldiçoada para que outra criatura recupere uma quantidade de pontos de vida igual a metade da cura original.", 4, support),

                // Level 6 abilities
                new ClassAbility("Contra-Ataque", "Uma quantidade de vezes igual ao dobro do seu modificador de Presença ou Sabedoria, por descanso curto ou longo, você pode, como uma reação, gastar 1 ponto de energia amaldiçoada para aumentar a Defesa de um aliado.", 6, support),
                new ClassAbility("Cura Avançada em Grupo", "Você pode usar sua habilidade de cura em grupo: quando a utilizar em um alvo, você pode pagar 2 pontos de energia amaldiçoada para curar mais um alvo.", 6, support),
                new ClassAbility("Devolver na Mesma Moeda", "Quando um aliado que você possa ver é afetado por uma condição, você pode gastar 2 PE para, como uma Ação Livre, fazer com que o próximo teste de resistência realizado por um inimigo para evitar uma condição do aliado possua desvantagem.", 6, support),
                new ClassAbility("Disseminar Cura", "Ao utilizar um Feitiço de cura, você pode escolher um alvo adicional, gastando uma quantidade de PE igual ao nível da técnica adicional.", 6, support),
                new ClassAbility("Incitar Vigor", "Você é capaz de utilizar de processos para incitar o vigor em uma criatura, puxando de seu potencial latente.", 6, support),
                new ClassAbility("Inimigo Comum", "Você pode gastar 2 pontos de energia amaldiçoada para, como uma ação bônus, escolher um inimigo comum entre uma quantidade de pessoas igual ao seu modificador de Presença ou sabedoria.", 6, support),
                new ClassAbility("Posicionamento Estratégico", "Em certos momentos, você não precisa se mover, mas outros se beneficiariam de um melhor posicionamento.", 6, support),

                // Level 8 abilities
                new ClassAbility("Aptidões de Suporte", "Você aprimora suas aptidões de energia necessárias para ser um grande suporte.", 8, support),
                new ClassAbility("Contaminar com Determinação", "Uma vez por cena, você pode gastar 4 pontos de energia amaldiçoada para, como uma ação comum, fazer com que você e dois aliados recebam vantagem em todo teste de resistência por duas rodadas.", 8, support),
                new ClassAbility("Criar Medicina", "Nem sempre é possível estar próximo aos seus aliados, então você desenvolve uma técnica para criar remédios portáteis.", 8, support, "Treinado em Ferramentas de Médico"),
                new ClassAbility("Cura Aperfeiçoada", "Sua cura é quase perfeita em sua consistência.", 8, support),
                new ClassAbility("Elevar Sucesso", "Como um suporte, você consegue elevar a tentativa de resistência de um aliado.", 8, support),
                new ClassAbility("Físico Controlado", "Você controla o seu físico a partir dos conhecimentos médicos e da energia amaldiçoada.", 8, support, "Treinado em Fortitude"),
                new ClassAbility("Motivação pelo Triunfo", "Neutralizar um dos inimigos incentiva você e seus aliados a continuarem lutando, independente de quem o tenha eliminado.", 8, support),
                new ClassAbility("Pressão do Médico", "Ao entrar nas portas da morte, você não fica inconsciente.", 8, support, "Mestre em Medicina"),
                new ClassAbility("Sustentação Avançada", "Seu corpo agora é capaz de dividir a liberação de energia entre dois feitiços diferentes.", 8, support),

                // Level 10 abilities
                new ClassAbility("Descarga Reanimadora", "Você descobriu uma técnica para descarregar energia reversa de maneira a reanimar imediatamente alguém caído.", 10, support, "Aptidão Cura Amplificada"),
                new ClassAbility("Necessidade de Continuar", "Para você, continuar presente no campo de batalha é mais do que uma necessidade, pois você é o suporte necessário.", 10, support, "Treinado em Vontade"),
                new ClassAbility("Olhar Aguçado", "Seus olhos são treinados para encontrar os pontos fracos dos inimigos.", 10, support, "Treinado em Percepção"),
                new ClassAbility("Táticas Defensivas", "Você pode escolher um tipo de dano Elemental para que você e dois aliados sejam resistentes.", 10, support),

                // Level 12 abilities
                new ClassAbility("Ajustes em Equipamento", "Você se torna capaz de fazer ajustes nos equipamentos dos seus aliados, durante um tempo de descanso.", 12, support, "Treinado em Ferramentas de Ferreiro"),
                new ClassAbility("Interferência", "Você se torna capaz de interferir nas ações dos inimigos.", 12, support),
                new ClassAbility("Não Desista!", "Ao ver um aliado atingir 0 ou menos de vida ao receber dano, você pode, gastando 3 de PE, fazer um teste de Persuasão contra a CD de estabilização.", 12, support),
                new ClassAbility("Sobrecura", "Ao curar um aliado você pode fazer com que essa cura supere o máximo de vida dele.", 12, support),
                new ClassAbility("Reação Necessária", "Você sabe que, em certos momentos, sua reação é necessária, mesmo que isso signifique ir além do esperado.", 12, support),

                // Level 14 abilities
                new ClassAbility("Apoio Abrangente", "Você é capaz de apoiar e melhorar isso de maneira mais abrangente.", 14, support, "Apoio Avançado"),

                // Level 16 abilities
                new ClassAbility("Purificação da Alma", "Suas capacidades se tornaram tão grandes que você inconscientemente se tornou ciente do traçado de uma alma, assim podendo curar diretamente as almas das pessoas.", 16, support),
                new ClassAbility("Sustentação Mestre", "Com o passar do tempo, você descobriu novas formas de como dispersar energia pelo seu corpo, conseguindo sustentar mais feitiços e com maior eficiência.", 16, support, "Sustentação Avançada"),

                // Advanced Supports (Apoios Avançados)
                new ClassAbility("Apoio Curativo", "Quando apoiar um aliado, você pode escolher gastar uma carga da habilidade Suporte em Combate para curar o aliado com ela como parte da ação.", 2, support),
                new ClassAbility("Apoio Defensivo", "Quando apoiar um aliado, você pode escolher aumentar a Defesa de dele em um valor igual metade do seu bônus de treinamento até o começo do próximo turno.", 2, support),
                new ClassAbility("Apoio Focado", "Quando apoiar um aliado, você pode escolher, além da vantagem, conceder um bônus no teste que ele realizar igual a metade do seu modificador de Presença ou Sabedoria.", 2, support),
                new ClassAbility("Apoio Ofensivo", "Quando apoiar um aliado, você pode gastar 2 PE para realizar um ataque como parte da ação.", 2, support),
                new ClassAbility("Apoio Estratégico", "Ao utilizar a ação de apoio, você pode aumentar a CD do próximo teste que force TR do Aliado em um valor igual a metade do seu Bônus de Treinamento.", 6, support)
        ));

        List<ClassAbility> restrictedAbilities = new ArrayList<>(
                        List.of(
                                // Level 2 abilities
                                new ClassAbility("Ataque Inconsequente", "Uma vez por rodada, ao realizar um ataque, você pode escolher atacar inconsequentemente: Você recebe vantagem na jogada de ataque e 5 na rolagem de dano dele. Porém, ao realizar um golpe inconsequente você fica Desprevenido por 1 rodada.", 2, restricted),
                                new ClassAbility("Apropriar-se", "Você recebe um bônus de 3 em testes para Desarmar ou evitar ser desarmado.", 2, restricted),
                                new ClassAbility("Aproximação Instintiva", "Quando um inimigo termina o turno dentro de uma distância igual a metade do seu deslocamento você pode, como uma ação livre, se mover até metade do seu movimento para um espaço mais próximo do inimigo. Essa movimentação não causa ataques de oportunidade e ignora terreno difícil. Caso, com essa movimentação, a criatura acabe em seu alcance de ataque, você pode gastar 2 pontos de estamina para realizar uma manobra contra ela.", 2, restricted),
                                new ClassAbility("Existência Imperceptível", "Com níveis mínimos de energia, você sabe como se esconder e tornar sua existência em algo imperceptível. Você recebe um bônus de 2 em rolagens de Furtividade. Além disso, sua penalidade em Furtividade por atacar e fazer outras ações chamativas é reduzida para 4.", 2, restricted),
                                new ClassAbility("Finta Melhorada", "Você encontra uma maneira de desenvolver a finta, sendo mais difícil prever seu próximo movimento. Você passa a poder somar o seu Modificador de Destreza, ao invés de Presença, em rolagens de Enganação para fintar. Além disso, acertar um inimigo desprevenido pela sua finta causa um dado de dano adicional.", 2, restricted),
                                new ClassAbility("Golpe Impactante", "Seu primeiro golpe encaixado é acompanhado de um grande impacto. Uma vez por rodada, ao realizar um ataque corpo a corpo contra um alvo, você pode também, como parte do mesmo ataque, realizar a ação de Empurrar contra o mesmo alvo. Caso tenha sucesso em empurrar, ele recebe Xd6 de dano adicional, onde X é igual a metade do seu modificador de Força.", 2, restricted),
                                new ClassAbility("Imitação", "Você consegue imitar técnicas e estilos de combate de outras pessoas, desde que tal não dependa da energia amaldiçoada. Ao ver uma habilidade ativa de especialização marcial, manobra ou postura, você pode escolher a copiar como uma reação, e deve a usar no seu próximo turno, ou perderá a cópia. Você só pode manter uma coisa copiada por vez, e só usa uma vez cada uma delas. Porém, quando copiar algo, você pode tentar aprender aquilo, realizando um teste de percepção com CD35, a qual diminui em 2 para cada vez que você copiar a mesma habilidade e tentar a aprender. Se suceder em aprender, você não precisa ver alguém a usando para poder copiar, necessitando de uma ação bônus, e a quantidade de usos se torna a quantidade padrão da habilidade, ao invés de uma só. Você pode aprender uma habilidade ativa e uma postura ou manobra; durante um interlúdio você pode escolher trocar uma habilidade aprendida por outra que possa ver durante o interlúdio, tentando a copiar com o teste de percepção, o qual é feito com vantagem. Caso o que for copiado gaste energia amaldiçoada, você paga o custo em pontos de estamina.", 2, restricted),
                                new ClassAbility("Manejo Superior", "Você sabe manejar armas como ninguém, extraindo seu máximo. O dano de toda arma que você manejar conta como um nível acima e suas rolagens de dano recebem um bônus igual ao seu bônus de treinamento.", 2, restricted),
                                new ClassAbility("Roubo de Habilidade", "Em busca de se adaptar, você consegue até mesmo roubar as habilidades dos outros. Ao obter essa habilidade, você pode aprender uma habilidade de Especialista em Combate ou Lutador, desde que tal não dependa do uso de energia amaldiçoada. Você usa seus níveis de Restringido para os requisitos. Você pode pegar essa habilidade uma quantidade de vezes igual ao seu bônus de treinamento, roubando habilidades diferentes. Você não pode roubar habilidades base das outras especializações, exceto Golpe Especial.", 2, restricted),
                                new ClassAbility("Surto de Adrenalina", "Como uma ação livre, você pode gastar 3 pontos de estamina para entrar em um estado onde seu corpo está no limite. Enquanto em um surto de adrenalina, você recebe os seguintes benefícios: você recebe redução de dano a todos os tipos de dano igual a metade do seu nível de personagem, você recebe um bônus igual a 1 mais metade do seu bônus de treinamento em testes de resistência de fortitude e reflexos, e você recebe um bônus em percepção igual ao seu bônus de treinamento. Um surto dura uma rodada, e você pode gastar 1 ponto de estamina adicional para cada rodada após a primeira que deseje o manter ativo.", 2, restricted),
                                new ClassAbility("Valorizar Invocação", "Tendo domado maldições, elas se tornam invocações úteis dentro de combate, e você passa a valorizar elas quando necessário. Caso uma das suas invocações dentro de 3 metros vá ser exorcizada, você pode gastar 1 ponto de estamina e usar sua reação para se colocar a frente dela, recebendo o golpe letal em troca de manter a invocação viva. Caso vá defender uma invocação, você recebe pontos de vida temporários igual ao seu nível de personagem.", 2, restricted),
                                // Level 4 abilities
                                new ClassAbility("Ação Ágil", "Você otimiza o seu tempo de ação. Uma vez por turno você pode gastar 2 PE para receber uma Ação Ágil, a qual pode ser utilizada para: Andar, Desengajar ou Esconder.", 4, restricted),
                                new ClassAbility("Adrenalina Intensificadora", "Sua adrenalina também intensifica o seu corpo e as suas capacidades. Ao entrar em um surto de adrenalina, você pode escolher pagar 2 pontos de estamina adicionais para poder distribuir um bônus de 4 entre as perícias de Atletismo e Acrobacia, da maneira que desejar (3 em uma e 1 em outra, por exemplo), além de poder pagar 1 ponto de estamina para se conceder vantagem em uma rolagem de Atletismo e Acrobacia, uma vez por cena cada. Ao obter a Restrição Definitiva, o bônus de 4 se torna 8.", 4, restricted),
                                new ClassAbility("Caçador de Feiticeiros", "Sua especialização é conseguir lidar com feiticeiros, preparando-se para os caçar, tanto resistindo melhor quanto destruindo melhor. No começo de uma cena você pode gastar 2 pontos de estamina para receber 2 de RD, 1 em testes de resistência e ataques, além de causar 1d6 de dano contra todos os feiticeiros presentes na cena. A cada 5 níveis você pode gastar mais 2 pontos para aumentar os bônus; 2 de RD, 1 de bônus e 1d6 de dano para cada 2 pontos adicionais.", 4, restricted),
                                new ClassAbility("Desenvolver Ideias", "Você tem uma percepção de como desenvolver as suas ideias de técnicas marciais e manobras, expandindo o seu repertório. Você recebe duas técnicas marciais adicionais ao obter essa habilidade.", 4, restricted),
                                new ClassAbility("Foco no Inimigo", "Ao iniciar um combate, você pode gastar 2 pontos de estamina e escolher um inimigo para ser seu foco. Ao atacar o inimigo que é seu foco você recebe um bônus de 2 para acertar e causa 1d6 de dano a mais, que aumenta para 1d8 no nível 6, 1d10 no nível 12 e 1d12 no nível 16, além de receber 5 em testes de Percepção para procurar o inimigo e em sua Atenção contra ele. Ao matar o inimigo em que você possui foco, você pode usar sua reação para passar o foco para outro inimigo dentro de 9 metros de você. Caso ataque outra criatura que não seja seu foco, a habilidade se encerra.", 4, restricted),
                                new ClassAbility("Ponto Cego", "Você consegue sempre perceber um ponto cego na guarda do inimigo, se posicionando em tal. Se mover pelo espaço de um inimigo não conta como terreno difícil, e sempre que você estiver no espaço de um inimigo, você recebe camuflagem leve, fazendo com que ataques contra você tenham 20% de chance de falhar (1 ou 2 em 1d10). A partir do 10° nível, você pode realizar uma rolagem de furtividade contra um alvo o qual esteja dentro do espaço dele; caso seu resultado seja superior ao valor de atenção dele, você passa a receber uma camuflagem total, fazendo com seus ataques tenham 40% de chance de falhar (1 a 4 em 1d10).", 4, restricted),
                                new ClassAbility("Resiliência pela Adrenalina", "A adrenalina pulsando no seu corpo o deixa mais resiliente e resistente. Sempre que você realizar um teste de resistência durante um Surto de Adrenalina, você pode pagar 1 ponto de estamina para adicionar 2d3 ao resultado. Caso seja um teste em que você não seja treinado, e se você falhar, você pode rolar novamente.", 4, restricted, "Surto de Adrenalina"),
                                new ClassAbility("Técnicas de Memorização", "Você estuda e se versa em uma maneira de conseguir memorizar uma quantidade maior de fatores. Ao obter essa habilidade, você pode aprender uma habilidade adicional a partir da Imitação. Caso tenha a habilidade Imitação Perfeita, você pode aprender mais uma habilidade adicional.", 4, restricted, "Imitação"),
                                // Level 6 abilities
                                new ClassAbility("Aprimoramento Celeste", "Você passa a somar metade do modificador do seu atributo chave em sua CD de Especialização.", 6, restricted),
                                new ClassAbility("Ataque Extra", "Você consegue atacar mais rápido, otimizando seus golpes. Ao realizar a ação Atacar, você pode gastar 2 pontos de estamina para atacar duas vezes ao invés de uma.", 6, restricted),
                                new ClassAbility("Ataque Inconsequente Aprimorado", "O bônus em dano ao usar o ataque inconsequente aumenta para 10 e, ao utilizar a habilidade, você recebe 2d6 4 pontos de vida temporária.", 6, restricted, "Ataque Inconsequente"),
                                new ClassAbility("Corpo de Aço", "Seu corpo é tão duro quanto o aço e não se curva, mantendo sua integridade. Seus pontos de vida máximos aumentam em um valor igual ao seu valor de Constituição, e você pode pagar 2 pontos de estamina para, durante uma cena, se curar em um valor igual a 2d8 mais seu modificador de constituição no começo de todo turno seu. No nível 10, você pode pagar 1 ponto de estamina adicional para aumentar a cura em 1d8, assim como pode pagar mais 1 ponto no nível 15 para aumentar novamente.", 6, restricted),
                                new ClassAbility("Corredor Fantasma", "Ao se mover, você pode utilizar o movimento para andar em paredes, no entanto, não pode terminar seu turno em uma. Caso termine, você cai, respeitando as regras de queda. Você recebe um bônus de 2 em testes para reduzir dano de queda. Caso possua a dádiva Agilidade Exímia, você pode correr em tetos.", 6, restricted),
                                new ClassAbility("Disparada Trovejante", "Você consegue usar da sua agilidade para disparar como um trovão em reação a um golpe. Ao receber um ataque corpo-a-corpo, você pode gastar 3 pontos de estamina para reduzir o dano a metade e se mover até 4,5 metros para longe do atacante.", 6, restricted),
                                new ClassAbility("Frenesi", "Durante o Surto de Adrenalina, você assume um frenesi intenso que aumenta o potencial ofensivo dos seus golpes: sempre que realizar um ataque, ele causa 4 de dano adicional. No 12° nível, esse bônus se torna 8, no 16° nível ele se torna 12.", 6, restricted, "Surto de Adrenalina"),
                                new ClassAbility("Movimento Reativo", "Uma vez por rodada, quando um oponente dentro de um alcance igual ao seu movimento iniciar a realização de uma ação que permitiria o uso de um ataque de oportunidade, você pode gastar 2 pontos de estamina para se locomover até ele com uma ação livre, e então gastar sua reação para executar o ataque de oportunidade.", 6, restricted),
                                // Level 8 abilities
                                new ClassAbility("Ainda de Pé", "Uma vez por descanso curto ou longo, quando você for chegar a 0 pontos de vida e cair você pode escolher se manter de pé e curar em 3d10 mais nível de personagem, aumentando em 1d10 nos níveis 12, 16 e 20. Caso o dano fosse suficiente para ser uma morte instantânea, você apenas resiste e fica com 1 de vida, caindo com uma falha no próximo dano que receber. Se você morrer, você morre de pé.", 8, restricted),
                                new ClassAbility("Arremetida Encoberta", "Ao realizar o Ataque Furtivo da rodada, você recebe vantagem no golpe. Caso o acerto dele já tenha sido garantido por qualquer motivo, você recebe 1d no dano do Ataque Furtivo.", 8, restricted),
                                new ClassAbility("Barreira Inamovível", "Sempre que você fizer um teste de resistência de Fortitude e o resultado natural do dado for menor do que seu modificador de Constituição, você pode gastar 2 pontos de estamina para transformar o resultado natural do dado no seu modificador de Constituição. Você não pode ser movido a força e tem vantagem para resistir a ser agarrado.", 8, restricted),
                                new ClassAbility("Força Imparável", "Sempre que você fizer um TR de Reflexos e o resultado natural do dado for menor do que seu modificador de Destreza, você pode gastar 2 pontos de estamina para transformar o resultado natural do dado no seu modificador de Destreza. Você se torna treinado em um teste de resistência à sua escolha e mestre em outro TR no qual já seja treinado.", 8, restricted),
                                new ClassAbility("Imitação Perfeita", "Você desenvolve a habilidade de imitação. Você se torna capaz de copiar habilidades passivas de especializações marciais e estilos de combate. Ao copiá-las, o efeito dura até o final do seu próximo turno. Você passa a poder aprender também uma habilidade passiva e um estilo de combate, mas é mais difícil, por ser algo sutil; a CD é igual a 40, e continua diminuindo em 2 por tentativa na mesma habilidade.", 8, restricted, "Imitação"),
                                new ClassAbility("Presença Ameaçadora", "Sua mera presença é ameaçadora, de tão poderoso você se mostra, mesmo sem energia amaldiçoada. Você pode gastar 1 ponto de estamina para demarcar a sua presença, fazendo com que toda criatura que consiga o ver realize um teste de resistência de vontade. Em uma falha, a criatura fica amedrontada por 2 rodadas, em um sucesso, fica abalada. Você só pode usar essa habilidade uma vez por cena em cada criatura.", 8, restricted),
                                new ClassAbility("Reação Rápida", "Com um tempo de reação grandemente desenvolvido, você consegue incitar o seu corpo a reagir com uma rapidez extrema. Caso já tenha gasto a sua reação, você pode pagar 2 pontos de estamina para realizar uma reação adicional, uma vez por rodada.", 8, restricted),
                                new ClassAbility("Respeito Celeste", "Seu poder e desenvolvimento te garantem o respeito dos céus, que concedem a sua benção para si. Ao obter essa habilidade, você recebe uma dádiva do céu adicional. A partir do nível 12, você pode pegar esta habilidade outra vez.", 8, restricted),
                                // Level 10 abilities
                                new ClassAbility("Assassinar", "Durante o primeiro momento, você é capaz de extrair letalidade absoluta, golpeando um inimigo desprevenido com um bote poderoso. Durante a primeira rodada de um combate, ao atacar uma criatura desprevenida a partir da furtividade ou surpresa, seu primeiro ataque é um crítico garantido.", 10, restricted),
                                new ClassAbility("Mente Limpa", "Você recebe vantagem para resistir às seguintes condições: Amedrontado, Cego, Enfeitiçado e Surdo.", 10, restricted),
                                new ClassAbility("Perceber o Ar", "Sua visão se torna tão apurada que você consegue perceber o próprio ar, usando-o como uma plataforma para se mover e apoiar. Você é imune a danos de queda, conseguindo se apoiar no ar, desde que a altura não seja superior ao dobro do seu movimento. Ao pular você pode realizar outro pulo em seguida, no nível 13 você pode dar dois pulos em seguida, e no nível 17 pode dar três pulos em seguida. Quando for alvo de um ataque, você pode gastar 2 pontos de estamina e sua reação para realizar um teste de acrobacia contra um teste de reflexos do atacante e, caso o resultado do seu teste supere o do atacante, você desvia do ataque.", 10, restricted),
                                new ClassAbility("Precisão Forçada", "Você consegue usar do seu físico impecável para forçar precisão absoluta em um golpe. Uma vez por rodada, quando você faz um ataque corpo-a-corpo, você pode pagar 3 pontos de estamina. Se acertar o ataque, causa dano máximo, sem necessidade de rolar danos.", 10, restricted),
                                new ClassAbility("Retaliação", "Se você receber dano de um inimigo que esteja dentro de seu alcance, você pode gastar 2 pontos de estamina e usar sua reação para realizar um ataque contra ele.", 10, restricted),
                                // Level 12 abilities
                                new ClassAbility("Adrenalina Absoluta", "Enquanto está em um surto de adrenalina, você se torna absoluto, extraindo ao máximo o seu potencial. Ao iniciar um surto de adrenalina, você pode escolher pagar 4 pontos para ativar e 2 por rodada para manter e, caso o faça, você recebe os seguintes benefícios: enquanto estiver em um surto de adrenalina, o seu ataque extra passa a custar 1 PE, você recebe 3 metros de Deslocamento e a sua DEF aumenta em 2.", 12, restricted, "Surto de Adrenalina"),
                                new ClassAbility("Pináculo Físico", "Você recebe 4 pontos de estamina máximos e pode escolher aumentar o valor de dois atributos entre Força, Destreza e Constituição em 2. No nível 16, o valor de ambos os atributos escolhidos aumentam novamente em 2.", 12, restricted),
                                new ClassAbility("Rejeitar a Morte", "Quando estiver nas portas da morte, você pode escolher receber uma falha garantida para fazer um teste de Fortitude contra a CD X, sendo X igual a 15 mais 1 para cada 3 pontos de vida negativos. Se passar, você fica com 1 de vida e recebe 1 ponto de exaustão.", 12, restricted, "Ainda de Pé"),
                                // Level 16 abilities
                                new ClassAbility("Entre as Sombras", "Agora o Ataque Furtivo aplica quando você está em camuflagem ou cobertura. Além disso, quando for realizar um Ataque Furtivo, você pode ignorar parcialmente as regras de vantagem e acumular até uma vantagem adicional (totalizando 3d20). Caso ele seja um acerto garantido, além do efeito normal, a sua margem de crítico é reduzida em 2.", 16, restricted, "Arremetida Encoberta"),
                                new ClassAbility("Instintos Aguçados", "Enquanto seus pontos de estamina e de vida excederem metade do máximo deles, você recebe uma reação adicional por rodada.", 16, restricted, "Reação Rápida"),
                                new ClassAbility("Mesmo Morto", "Mesmo se você não tiver mais força vital, é necessário continuar lutando até o limite. Ao cair para 0 de vida, sem possuir um uso de Ainda de Pé, ao invés de ir para as Portas da Morte você continua de pé e realizando seus turnos normalmente; porém, no final de todo turno, você deve realizar um teste de resistência de Fortitude com CD25 mais 1 para cada 5 pontos de vida negativos que possuir. Caso falhe no teste, você cai imediatamente, com 1 falha nos testes de morte.", 16, restricted, "Rejeitar a Morte")
                        )
                );

        List<List<ClassAbility>> classAbilities = List.of(
                fighterAbilities,
                combatSpecialistAbilities,
                techniqueSpecialistAbilities,
                controllerAbilities,
                supportAbilities,
                restrictedAbilities
        );

        List<ClassAbility> allAbilities = new ArrayList<>();
        allAbilities.addAll(fighterAbilities);
        allAbilities.addAll(combatSpecialistAbilities);
        allAbilities.addAll(techniqueSpecialistAbilities);
        allAbilities.addAll(controllerAbilities);
        allAbilities.addAll(supportAbilities);
        allAbilities.addAll(restrictedAbilities);

        if (classAbilityRepository.count() == 0) {
            classAbilityRepository.saveAll(allAbilities);
            characterClassRepository.saveAll(List.of(
                    fighter,
                    combatSpecialist,
                    techniqueSpecialist,
                    controller,
                    support,
                    restricted
            ));
        }
    }
}
