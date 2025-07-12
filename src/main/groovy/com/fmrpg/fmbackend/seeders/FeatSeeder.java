package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.characterpkg.CharacterFeat;
import com.fmrpg.fmbackend.repositories.FeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FeatSeeder implements CommandLineRunner {

    private final FeatRepository featRepository;



    @Override
    public void run(String... args) throws Exception {

        if(featRepository.count() == 0 ) {
            List<CharacterFeat> feats = new ArrayList<>(
                    List.of(
                            new CharacterFeat("Afinidade com Técnica", "Com uma afinidade superior com a sua técnica amaldiçoada, você consegue a desenvolver melhor, criando mais extensões dela.", "Você recebe um Feitiço adicional. Nos níveis 5, 10, 15 e 20 você recebe mais um Feitiço adicional."),
                            new CharacterFeat("Artesão Amaldiçoado", "A criação de ferramentas amaldiçoadas é um ofício no qual você busca se especializar.", "Você pode criar Ferramentas Amaldiçoadas. Torna-se treinado em Ofício (Ferreiro) ou Ofício (Canalizador), ou mestre se já treinado em ambos."),
                            new CharacterFeat("Ataque Infalível", "Uma vez por rodada, após a rolagem de dano de um ataque, você pode escolher repetir.", "Você fica com o novo resultado e não pode ter os níveis de dano da sua arma reduzidos."),
                            new CharacterFeat("Atenção Infalível", "Sua atenção para os arredores nunca falha.", "+5 em Atenção e você não pode ser surpreendido se estiver consciente."),
                            new CharacterFeat("Dedicação Recompensadora", "Você se dedica mais em suas missões e recebe melhores recompensas.", "Recebe itens extras conforme seu grau. Os bônus não são cumulativos."),
                            new CharacterFeat("Favorecido pela Sorte", "Você tem uma sorte inexplicável que o favorece em momentos críticos.", "3 pontos de sorte. Pode gastar para rerrolar d20. Recupera sorte ao sofrer acerto crítico. Recupera tudo após descanso longo."),
                            new CharacterFeat("Guarda Infalível", "Você nunca baixa a guarda, mesmo em situações catastróficas.", "Ignora desastre em ataque, +3 contra redução de Defesa ou penalidades em testes de resistência."),
                            new CharacterFeat("Incremento de Atributo", "Você aumenta um de seus atributos com treino e esforço.", "Aumenta valor e limite de um atributo à sua escolha em 2. Pode ser escolhido várias vezes (uma por atributo)."),
                            new CharacterFeat("Investida Aprimorada", "Você domina a arte de realizar investidas.", "+3m de movimento na investida, bônus de acerto igual ao bônus de treinamento, alvo faz teste de Atletismo ou é derrubado."),
                            new CharacterFeat("Mestre das Armas", "Você melhora sua capacidade com armas.", "Aumenta Força ou Destreza em 2. Escolha: treinar em 4 armas ou obter efeito de crítico de um grupo de armas."),
                            new CharacterFeat("Mestre Defensivo", "Você aprimora suas capacidades defensivas.", "Aumenta Força ou Constituição em 2. Treinado em escudos ou melhora a RD com escudos se já treinado."),
                            new CharacterFeat("Perceber Oportunidade", "Você tem uma habilidade ímpar para golpes oportunos.", "Pode realizar 2 Golpes de Oportunidade por rodada. Golpes possuem vantagem."),
                            new CharacterFeat("Provocação Desafiadora", "Você se torna o centro das atenções de quem provoca.", "Alvo deve atacar você e tem desvantagem para atacar outros. Pode usar Provocar como Ação Livre algumas vezes por cena."),
                            new CharacterFeat("Resiliência Melhorada", "Você resiste melhor a uma ameaça específica.", "Escolha um teste de resistência (exceto Integridade): torna-se treinado ou mestre. Atributo usado no TR aumenta em 1."),
                            new CharacterFeat("Saltador Constante", "Você se move mais pulando que correndo.", "Pode pular novamente como Ação Livre em certas condições. +2 em ataque ao final do pulo e dano adicional se acertar."),
                            new CharacterFeat("Técnicas Agressivas de Escudo", "Você aperfeiçoa o escudo para usá-lo ofensivamente.", "Pode usar Ação Bônus para Empurrar com escudo. Alvo sofre dano se for empurrado e pode ser derrubado ou empurrado mais longe."),
                            new CharacterFeat("Técnicas de Arremesso", "Você domina o uso de armas de arremesso.", "+2 para acertar e +3 no dano com armas de arremesso."),
                            new CharacterFeat("Técnicas de Reação Rápida", "Você reage rapidamente sob pressão.", "+5 de Iniciativa. Pode rerrolar a Iniciativa e ficar com o melhor resultado caso não seja o primeiro."),
                            new CharacterFeat("Técnicas Defensivas de Escudo", "Você aprimora o uso do escudo na defesa.", "Adiciona bônus do escudo em TR de Reflexos. Pode reduzir valor de sucesso crítico em Reflexos usando Reação algumas vezes por descanso."),
                            new CharacterFeat("Tempestade de Ideias", "Você busca seu máximo potencial.", "+1 em um atributo, treinado em uma perícia e ferramenta, pode receber vantagem em testes de uma perícia treinada algumas vezes por descanso."),
                            new CharacterFeat("Adepto de Medicina", "Você estudou primeiros socorros para ajudar aliados.", "Acesso ao segundo efeito de Suporte em Combate, cura baseada no nível, com metade dos usos. [Pré-Requisito: Mestre em Medicina, máx 2 talentos 'Adepto']"),
                            new CharacterFeat("Adepto de Briga", "Você treinou seus punhos para combate desarmado.", "+3 em ataque desarmado, dano aumenta em 2 níveis. Pode Agarrar/Derrubar/Empurrar como Ação Livre. [Pré-Requisito: Mestre em Atletismo, máx 2 talentos 'Adepto']"),
                            new CharacterFeat("Adepto de Combate", "Você adota um estilo de combate específico.", "Aprende um estilo de combate do Especialista em Combate. [Pré-Requisito: Mestre em Intuição, máx 2 talentos 'Adepto']"),
                            new CharacterFeat("Adepto de Feitiçaria", "Você explora melhor a feitiçaria.", "Recebe uma Mudança de Fundamento (exceto Técnica Rápida). Pode reduzir o custo algumas vezes por cena. [Pré-Requisito: Mestre em Feitiçaria, possuir Feitiços, máx 2 talentos 'Adepto']"),
                            new CharacterFeat("Alma Inquebrável", "Sua alma resiste mais do que o comum.", "Treinado em Integridade. Redução de Dano contra dano na alma igual a 1/4 do nível. [Pré-Requisito: Constituição 14]"),
                            new CharacterFeat("Apaziguador de Técnica", "Você sabe como interromper técnicas inimigas.", "Golpe de oportunidade ao ver técnica sendo usada. Inimigo deve passar em teste de concentração. Efeitos variam. [Pré-Requisito: Treinado em Astúcia, Nível 8]"),
                            new CharacterFeat("Aptidão Desenvolvida", "Você melhora sua capacidade de usar a energia amaldiçoada.", "Escolha uma Aptidão Amaldiçoada para aumentar em 1. Pode ser escolhido várias vezes (uma por Aptidão). [Pré-Requisito: Nível 4]"),
                            new CharacterFeat("Determinado a Viver", "Você mantém sua vontade de viver mesmo em situações extremas.", "Uma vez por dia, evita cair inconsciente e fica com 1 de vida. Testes de morte posteriores têm vantagem. [Pré-Requisito: Treinado em Vontade, Constituição 16]"),
                            new CharacterFeat("Discurso Motivador", "Você inspira seus aliados com palavras encorajadoras.", "Aliados recebem HP temporário baseado no seu nível e carisma. Usável 1x por descanso. [Pré-Requisito: Treinado em perícia de Carisma]")
                    )
            );

            featRepository.saveAll(feats);
        }


    }
}
