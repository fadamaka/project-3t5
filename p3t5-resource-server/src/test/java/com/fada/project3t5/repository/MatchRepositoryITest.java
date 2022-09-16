package com.fada.project3t5.repository;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.fada.project3t5.domain.Match;
import com.fada.project3t5.domain.MatchStatus;
import com.fada.project3t5.domain.Move;
import com.fada.project3t5.domain.Player;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MatchRepositoryITest {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    @SneakyThrows
    void test() {
        playerRepository.saveAll(List.of(Player.builder().name("one").apiKey("unimplemented").build(),
                Player.builder().name("two").apiKey("unimplemented").build()));
        List<Player> players = playerRepository.findAll();

        matchRepository.saveAll(List.of(
                Match.builder()
                        .p1(players.get(0))
                        .p2(players.get(1))
                        .status(MatchStatus.FINISHED)
                        .movesMap(Map.of(1, new Move("x", 1, 2), 2, new Move("o", 1, 3)))
                        .build(),
                Match.builder()
                        .p1(players.get(1))
                        .p2(players.get(0))
                        .status(MatchStatus.FINISHED)
                        .movesMap(Map.of(1, new Move("x", 1, 2), 2, new Move("o", 1, 3)))
                        .build()));
        List<Match> matches = matchRepository.findAll();
        Assertions.assertThat(matches.size()).isEqualTo(2);
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.setVisibility(PropertyAccessor.FIELD,
        // JsonAutoDetect.Visibility.ANY);
        log.info(objectMapper.writeValueAsString(matches));

    }

}