package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConsultasTest {

    private List<Player> lista = CsvUtilFile.getPlayers();
    private  Flux<Player> listFlux = Flux.fromIterable(lista);

    @Test
    void jugadoresMayoresA34(){
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .filter(player -> player.age >= 34 && player.club.equals("FC Schalke 04"))
                .distinct()
                .collectMultimap(Player::getClub);

        listFilter.subscribe(System.out::println);
        //listFlux.filter(e->e.age >34 && e.club.equals("FC Schalke 04")).subscribe(System.out::println);

    }
    @Test
    void consultarNacionalidades(){
        Mono<Map<Object, Player>> filtroNacionalidad =listFlux
                .collectMap(Player::getNational);

        filtroNacionalidad.subscribe(player-> System.out.println(player.keySet()));
    }

    @Test
    void rankingPaises(){
        Mono<Map<Object, Player>> ranking = listFlux
                .collectMap(player -> player.getWinners());
        ranking.subscribe(player-> System.out.println(player));

    }
}
