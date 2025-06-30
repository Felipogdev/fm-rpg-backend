package com.fmrpg.fmbackend.seeders;

import com.fmrpg.fmbackend.entities.Weapon;
import com.fmrpg.fmbackend.entities.WeaponGroup;
import com.fmrpg.fmbackend.entities.WeaponProperties;
import com.fmrpg.fmbackend.enums.DamageType;
import com.fmrpg.fmbackend.repositories.WeaponGroupRepository;
import com.fmrpg.fmbackend.repositories.WeaponPropertiesRepository;
import com.fmrpg.fmbackend.repositories.WeaponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WeaponDataSeeder implements CommandLineRunner {

    private final WeaponGroupRepository weaponGroupRepository;
    private final WeaponRepository weaponRepository;
    private final WeaponPropertiesRepository weaponPropertiesRepository;

    @Override
    public void run(String... args) throws Exception {


        if (weaponRepository.count() > 0) return;
        if (weaponGroupRepository.count() > 0) return;
        if (weaponPropertiesRepository.count() > 0) return;

        List<String> groupNames = List.of(
                "Faca", "Bastão", "Espada", "Pugilato", "Haste", "Machado",
                "Outro", "Chicote", "Martelo", "Arco", "Besta", "Tiro", "Dardo"
        );

        weaponGroupRepository.saveAll(
                groupNames.stream().map(WeaponGroup::new).toList()
        );

        Map<String, WeaponGroup> groupMap = new HashMap<>();
        for (String name : groupNames) {
            WeaponGroup group = weaponGroupRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Grupo " + name + " não encontrado"));
            groupMap.put(name, group);
        }
//
        Map<String, String> propertiesWithDescriptions = Map.ofEntries(
                Map.entry("Arremessável", "A arma pode ser usada para arremessos."),
                Map.entry("Duas Mãos", "A arma só pode ser manuseada de maneira apropriada com as duas mãos."),
                Map.entry("Especial", "A arma possui uma característica especial e única dela."),
                Map.entry("Estendida", "A arma tem o seu alcance para ataques aumentado em 1,5 metros."),
                Map.entry("Fineza", "A arma pode utilizar tanto força quanto destreza como modificador."),
                Map.entry("Leve", "Uma arma leve pode ser usada no manuseio de duas armas."),
                Map.entry("Marcial", "Uma arma marcial pode ser utilizada apropriadamente pelo Lutador."),
                Map.entry("Pesada", "Necessita de pelo menos 16 de força para ser usada."),
                Map.entry("Recarga", "Exige o uso de ação bônus para recarregar após ataques."),
                Map.entry("Versátil", "Pode ser usada com uma ou duas mãos, modificando o dano."),
                Map.entry("Adaga de Aparar", "Você recebe RD igual ao bônus de maestria contra dano físico."),
                Map.entry("Adagas Duplas", "Conta como arma de duas mãos, atacando com ambas ao mesmo tempo."),
                Map.entry("Canhão de Mão", "Afeta todas as criaturas a 3m do alvo. Requer ação para recarregar."),
                Map.entry("Chakram", "Pode voltar para a sua mão com teste de Prestidigitação CD12 + Distância."),
                Map.entry("Chicote de Corrente", "Permite usar a ação de Agarrar à distância."),
                Map.entry("Chicote Espinhento", "Causa 1d4 cortante e 1d4 perfurante."),
                Map.entry("Escopeta", "Cone de 3m. Rola contra a CA de cada uma. Recarrega com ação comum."),
                Map.entry("Espada de Gancho", "Pode ser combinada com outra para formar arma Estendida 2d6."),
                Map.entry("Espada Colossal", "Necessita de pelo menos 20 de força para ser manuseada."),
                Map.entry("Faixas", "Conta como desarmado e tem mesmo dano de ataque desarmado."),
                Map.entry("Kusarigama", "Ataca com foice (1d6 cortante) e peso (1d6 impacto). Vantagem para desarmar."),
                Map.entry("Lança Grande", "Desvantagem se estiver a 1,5m do alvo."),
                Map.entry("Leque", "Alterna entre impacto (fechado) e cortante (aberto)."),
                Map.entry("Manopla", "Dano desarmado aumenta para 1d6."),
                Map.entry("Metralhadora", "Ataque extra com ação bônus. Recarrega com ação comum."),
                Map.entry("Rede", "Enreda o alvo. -2 CA e ataques. Teste CD20 para escapar."),
                Map.entry("Soco Inglês", "Dano desarmado 1d4 + 1d4 a cada 2 de Força.")
        );

        weaponPropertiesRepository.saveAll(
                propertiesWithDescriptions.entrySet().stream()
                        .map(entry -> new WeaponProperties(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())
        );

        Map<String, WeaponProperties> propertyMap = new HashMap<>();
        for (String name : propertiesWithDescriptions.keySet()) {
            WeaponProperties prop = weaponPropertiesRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Propriedade " + name + " não encontrada"));
            propertyMap.put(name, prop);
        }


        weaponRepository.saveAll(
                List.of(
                        // Armas Simples
                        new Weapon("Adaga", 1, 1, "Uma faca leve e compacta, afiada e fácil de esconder, sendo uma favorita de pessoas furtivas por ser útil tanto para a força quanto para a agilidade. Ocupa um espaço.", List.of(1), List.of(4), 18, DamageType.PERFURANTE, groupMap.get("Faca"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Fineza"), propertyMap.get("Leve"), propertyMap.get("Marcial"))),
                        new Weapon("Bastão", 1, 1, "Um bastão, de madeira ou de aço, simples mas efetivo. Ocupa um espaço.", List.of(1), List.of(6), 20, DamageType.IMPACTO, groupMap.get("Bastão"), List.of(propertyMap.get("Versátil"))),
                        new Weapon("Clava", 1, 1, "Uma simples clava, comumente feita de madeira. Ocupa um espaço.", List.of(1), List.of(8), 20, DamageType.IMPACTO, groupMap.get("Bastão"), List.of(propertyMap.get("Versátil"))),
                        new Weapon("Espada Curta", 1, 1, "O tipo mais comum de espada, leve e rápida de se manusear. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.CORTANTE, groupMap.get("Espada"), List.of(propertyMap.get("Leve"), propertyMap.get("Marcial"))),
                        new Weapon("Faixas", 1, 1, "Faixas presas às mãos de um lutador desarmado, protegendo-as e podendo ser imbuídas com energia. Ocupa um espaço.", null, null, null, DamageType.IMPACTO, groupMap.get("Pugilato"), List.of(propertyMap.get("Especial"), propertyMap.get("Faixas"))),
                        new Weapon("Foice", 1, 1, "Uma pequena foice, normalmente usada como ferramenta, mas também servindo como arma. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.CORTANTE, groupMap.get("Haste"), List.of(propertyMap.get("Marcial"), propertyMap.get("Leve"), propertyMap.get("Fineza"))),
                        new Weapon("Lança", 1, 1, "Uma arma composta por uma haste de madeira e uma ponta afiada, de variáveis materiais. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.PERFURANTE, groupMap.get("Haste"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Estendida"), propertyMap.get("Versátil"))),
                        new Weapon("Leque", 1, 1, "Um leque metálico e cortante, capaz de alternar entre ambos os danos. Tão gracioso quanto letal. Ocupa um espaço.", List.of(1), List.of(4), 18, DamageType.IMPACTO, groupMap.get("Outro"), List.of(propertyMap.get("Especial"), propertyMap.get("Leque"))),
                        new Weapon("Machado", 1, 1, "Uma adaptação do que costumava ser uma ferramenta para se tornar uma arma. Ocupa um espaço.", List.of(1), List.of(8), 20, DamageType.CORTANTE, groupMap.get("Machado"), List.of(propertyMap.get("Versátil"))),
                        new Weapon("Mangual", 1, 1, "Uma haste metálica ligada a uma corrente com uma esfera de aço na ponta. Ocupa um espaço.", List.of(1), List.of(8), 20, DamageType.IMPACTO, groupMap.get("Chicote"), List.of()),
                        new Weapon("Manopla", 1, 1, "Uma manopla feita de aço, protegendo as mãos e as transformando em efetivas armas. Ocupa um espaço.", null, null, 19, DamageType.IMPACTO, groupMap.get("Pugilato"), List.of(propertyMap.get("Especial"), propertyMap.get("Manopla"))),
                        new Weapon("Martelo", 1, 1, "Uma haste de madeira resistente com uma cabeça de metal na ponta. Ocupa um espaço.", List.of(1), List.of(8), 20, DamageType.IMPACTO, groupMap.get("Martelo"), List.of(propertyMap.get("Versátil"))),
                        new Weapon("Tridente", 1, 1, "Uma arma antiga, semelhante a uma lança, mas com a sua ponta se dividindo em três. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.PERFURANTE, groupMap.get("Haste"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Estendida"), propertyMap.get("Versátil"))),

                        // Armas Simples a Distância
                        new Weapon("Arco Curto", 1, 1, "Um arco curto e comum, normalmente feito de madeira. Não é necessário recarregar com um arco curto, mas você deve estar utilizando uma aljava carregada com flechas. Ocupa dois espaços.", List.of(1), List.of(6), 20, DamageType.PERFURANTE, groupMap.get("Arco"), List.of(propertyMap.get("Duas Mãos"))),
                        new Weapon("Besta Leve", 1, 1, "Um arco montado sobre uma coronha e com um gatilho embutido, disparando com maior potência. Uma besta leve precisa recarregar a cada disparo. Ocupa um espaço.", List.of(1), List.of(8), 19, DamageType.PERFURANTE, groupMap.get("Besta"), List.of(propertyMap.get("Recarga"), propertyMap.get("Leve"))),
                        new Weapon("Pistola", 2, 1, "A mais simples arma de fogo, facilmente manuseada, mas ainda letal. Uma pistola precisa recarregar a cada doze disparos. Ocupa um espaço.", List.of(2), List.of(6), 19, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Recarga"))),
//
//                        // Armas Simples de Arremesso
                        new Weapon("Azagaia", 1, 1, "Uma lança leve e flexível, própria para ser arremessada.", List.of(1), List.of(6), 20, DamageType.PERFURANTE, groupMap.get("Dardo"), List.of(propertyMap.get("Arremessável"))),
                        new Weapon("Dardo", 1, 1, "Um dardo leve e simples para arremessos rápidos.", List.of(1), List.of(4), 18, DamageType.PERFURANTE, groupMap.get("Dardo"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Leve"))),
//
//                        // Armas Complexas
                        new Weapon("Adagas Duplas", 2, 2, "Duas adagas presas uma a outra através de correntes e ligadas ao portador, dificultando o desarme e permitindo golpes rápidos com ambas. Ocupa dois espaços.", List.of(2), List.of(4), 18, DamageType.PERFURANTE, groupMap.get("Faca"), List.of(propertyMap.get("Especial"), propertyMap.get("Adagas Duplas"))),
                        new Weapon("Adaga de Aparar", 2, 2, "Uma pequena adaga feita para ser usada de maneira defensiva, pequena e leve, com uma guarda mais ampla e proteção para as mãos. Ocupa um espaço.", List.of(1), List.of(3), 19, DamageType.PERFURANTE, groupMap.get("Faca"), List.of(propertyMap.get("Especial"), propertyMap.get("Adaga de Aparar"), propertyMap.get("Fineza"))),
                        new Weapon("Alabarda", 1, 2, "Uma arma longa, composta de uma haste e uma lâmina semelhante à de um machado presa na ponta. Ocupa dois espaços.", List.of(1), List.of(10), 20, DamageType.CORTANTE, groupMap.get("Haste"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Estendida"))),
                        new Weapon("Chicote", 1, 1, "Um chicote forte e reforçado, adaptado para combate. Ocupa um espaço.", List.of(1), List.of(4), 18, DamageType.CORTANTE, groupMap.get("Chicote"), List.of(propertyMap.get("Estendida"), propertyMap.get("Fineza"))),
                        new Weapon("Chicote de Corrente", 2, 1, "Um chicote composto por correntes metálicas, tornando-se mais pesado e destruidor. Ocupa um espaço.", List.of(1), List.of(6), 20, DamageType.IMPACTO, groupMap.get("Chicote"), List.of(propertyMap.get("Especial"), propertyMap.get("Chicote de Corrente"))),
                        new Weapon("Chicote Espinhento", 2, 1, "Um chicote, normalmente de couro, coberto com espinhos perfurantes. Ocupa um espaço.", List.of(2), List.of(4), 19, DamageType.ESPECIAL, groupMap.get("Chicote"), List.of(propertyMap.get("Especial"), propertyMap.get("Chicote Espinhento"))),
                        new Weapon("Clava Pesada", 2, 2, "Uma clava ainda maior e mais pesada, com madeira reforçada. Ocupa dois espaços.", List.of(2), List.of(6), 20, DamageType.IMPACTO, groupMap.get("Bastão"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Corrente de Aço", 1, 1, "Uma corrente pesada de aço e longa, permitindo golpes mais distantes. Ocupa um espaço.", List.of(2), List.of(4), 20, DamageType.IMPACTO, groupMap.get("Chicote"), List.of(propertyMap.get("Estendida"))),
                        new Weapon("Espada de Gancho", 1, 1, "Uma espada com forma especial, tendo uma ponta em forma de gancho, o que permite prender duas para se criar uma arma mais longa. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.CORTANTE, groupMap.get("Espada"), List.of(propertyMap.get("Especial"), propertyMap.get("Espada de Gancho"), propertyMap.get("Fineza"))),
                        new Weapon("Espada Longa", 1, 1, "Uma arma típica para guerreiros, com uma lâmina reta e longa. Ocupa um espaço.", List.of(1), List.of(8), 20, DamageType.CORTANTE, groupMap.get("Espada"), List.of(propertyMap.get("Versátil"))),
                        new Weapon("Espada Grande", 2, 2, "Uma espada grande e pesada, precisando de duas mãos para se empunhar com efetividade. Ocupa dois espaços.", List.of(1), List.of(12), 20, DamageType.CORTANTE, groupMap.get("Espada"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Espada Colossal", 3, 4, "Uma espada de tamanho enorme e surreal, mas que pode ser empunhada por aqueles com uma enorme força. Ocupa quatro espaços.", List.of(2), List.of(8), 20, DamageType.CORTANTE, groupMap.get("Espada"), List.of(propertyMap.get("Especial"), propertyMap.get("Espada Colossal"), propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Foice Grande", 2, 2, "Uma foice maior, pensada realmente para o combate. Ocupa dois espaços.", List.of(1), List.of(10), 20, DamageType.CORTANTE, groupMap.get("Haste"), List.of(propertyMap.get("Versátil"), propertyMap.get("Pesada"))),
                        new Weapon("Kusarigama", 2, 2, "Uma arma japonesa composta por uma foice e um peso, ambos ligados por uma corrente, sendo ótima para desarmar. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.ESPECIAL, groupMap.get("Haste"), List.of(propertyMap.get("Especial"), propertyMap.get("Kusarigama"))),
                        new Weapon("Lança Grande", 2, 2, "Uma lança ainda mais longa e potente, o que dificulta seu manuseio para ataques próximos. Ocupa dois espaços.", List.of(1), List.of(12), 20, DamageType.PERFURANTE, groupMap.get("Haste"), List.of(propertyMap.get("Especial"), propertyMap.get("Lança Grande"), propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Machado Grande", 2, 2, "Um machado maior e com lâmina dupla. Ocupa dois espaços.", List.of(1), List.of(12), 20, DamageType.CORTANTE, groupMap.get("Machado"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Martelo Grande", 2, 2, "Um martelo com haste estendida e cabeça ainda maior. Ocupa dois espaços.", List.of(1), List.of(12), 20, DamageType.IMPACTO, groupMap.get("Martelo"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Nunchaku", 1, 1, "Dois bastões de madeira conectados por uma corda ou corrente. Ocupa um espaço.", List.of(1), List.of(6), 19, DamageType.IMPACTO, groupMap.get("Bastão"), List.of(propertyMap.get("Estendida"), propertyMap.get("Fineza"))),
                        new Weapon("Nunchaku Pesado", 2, 2, "Adicionando peso e potência, são dois bastões de metal conectados. Ocupa dois espaços.", List.of(2), List.of(6), 19, DamageType.IMPACTO, groupMap.get("Bastão"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"), propertyMap.get("Estendida"))),
                        new Weapon("Rapieira", 1, 1, "Uma espada leve com lâmina fina e perfurante, favorecendo a agilidade. Ocupa um espaço.", List.of(1), List.of(8), 19, DamageType.PERFURANTE, groupMap.get("Espada"), List.of(propertyMap.get("Fineza"))),
                        new Weapon("Soco Inglês", 2, 1, "Uma soqueira que se encaixa no dedo como anéis, amplificando o potencial de socos. Ocupa um espaço.", null, null, 20, DamageType.IMPACTO, groupMap.get("Pugilato"), List.of(propertyMap.get("Especial"), propertyMap.get("Soco Inglês"))),
//
//                        // Armas Complexas a Distância
                        new Weapon("Arco Longo", 1, 2, "Um arco reforçado e maior, sendo quase do tamanho de uma pessoa, assim permitindo disparos mais distantes. Não é necessário recarregar com um arco longo, mas você deve estar utilizando uma aljava carregada com flechas. Ocupa dois espaços.", List.of(1), List.of(8), 20, DamageType.PERFURANTE, groupMap.get("Arco"), List.of(propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Besta Pesada", 2, 2, "Uma versão mais potente e pesada da besta, amplificando na sua potência em troca da agilidade. Uma besta pesada precisa recarregar a cada disparo. Ocupa dois espaços.", List.of(1), List.of(12), 19, DamageType.PERFURANTE, groupMap.get("Besta"), List.of(propertyMap.get("Recarga"), propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),
                        new Weapon("Canhão de Mão", 3, 2, "Uma arma de fogo compacta, mas devastadora, diminuindo um canhão até um tamanho fácil de se carregar. Um canhão de mão precisa recarregar a cada disparo, utilizando uma ação comum. Ocupa dois espaços.", List.of(2), List.of(10), 20, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Especial"), propertyMap.get("Canhão de Mão"), propertyMap.get("Recarga"), propertyMap.get("Pesada"))),
                        new Weapon("Escopeta", 3, 2, "Uma arma de fogo devastadora em curto alcance, capaz de atingir vários inimigos em troca de uma recarga lenta. Uma escopeta precisa recarregar a cada dois disparos, utilizando uma ação comum. Ocupa dois espaços.", List.of(2), List.of(8), 20, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Especial"), propertyMap.get("Escopeta"), propertyMap.get("Recarga"), propertyMap.get("Duas Mãos"))),
                        new Weapon("Metralhadora", 3, 2, "Uma arma de fogo com disparo rápido e descarga potente, carregando várias balas. Uma metralhadora precisa recarregar a cada trinta disparos, utilizando uma ação comum. Ocupa dois espaços.", List.of(1), List.of(10), 19, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Especial"), propertyMap.get("Metralhadora"), propertyMap.get("Recarga"), propertyMap.get("Duas Mãos"))),
                        new Weapon("Rifle", 2, 2, "Um rifle comum de ferrolho, com alcance considerável e potência grande. Um rifle deve recarregar a cada vinte disparos. Ocupa dois espaços.", List.of(2), List.of(8), 19, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Recarga"), propertyMap.get("Duas Mãos"))),
                        new Weapon("Rifle de Precisão", 3, 2, "Um rifle munido com uma mira telescópica e capaz de realizar tiros a longa distância, preservando ainda a letalidade. Um rifle de precisão deve recarregar a cada cinco disparos. Ocupa dois espaços.", List.of(2), List.of(10), 19, DamageType.PERFURANTE, groupMap.get("Tiro"), List.of(propertyMap.get("Recarga"), propertyMap.get("Duas Mãos"), propertyMap.get("Pesada"))),

                        // Armas Complexas de Arremesso
                        new Weapon("Chakram", 1, 1, "Uma exótica arma de arremesso, com forma circular e bordas cortantes.", List.of(2), List.of(4), 20, DamageType.CORTANTE, groupMap.get("Dardo"), List.of(propertyMap.get("Especial"), propertyMap.get("Chakram"))),
                        new Weapon("Kunai", 1, 1, "Uma arma comumente associada aos ninjas, sendo uma lâmina de ferro com um furo na base, boa para arremesso.", List.of(1), List.of(6), 19, DamageType.PERFURANTE, groupMap.get("Dardo"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Leve"))),
                        new Weapon("Rede", 1, 1, "Uma rede de material forte e duradouro, feita para prender inimigos. Ocupa um espaço.", null, null, null, DamageType.ESPECIAL, groupMap.get("Outro"), List.of(propertyMap.get("Especial"), propertyMap.get("Rede"))),
                        new Weapon("Shuriken", 1, 1, "Uma arma de arremesso japonesa, sendo uma lâmina plana com várias pontas.", List.of(1), List.of(4), 18, DamageType.PERFURANTE, groupMap.get("Dardo"), List.of(propertyMap.get("Arremessável"), propertyMap.get("Leve")))
                )
        );


    }
}