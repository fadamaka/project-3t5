package com.fada.project3t5.repository;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fada.project3t5.domain.enums.MatchStatus;
import com.fada.project3t5.domain.enums.Sign;
import com.fada.project3t5.domain.model.Match;
import com.fada.project3t5.domain.model.Move;
import com.fada.project3t5.domain.model.Player;
import com.fada.project3t5.domain.model.Point;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MatchRepositoryITest {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void test() {
        playerRepository.saveAll(List.of(Player.builder().name("one").email("one@one.one").build(),
                Player.builder().name("two").email("two@two.two").build()));
        List<Player> players = playerRepository.findAll();

        matchRepository
                .saveAll(List.of(
                        Match.builder()
                                .p1(players.get(0))
                                .p2(players.get(1))
                                .status(MatchStatus.IN_PROGRESS)
                                .movesMap(Map
                                        .of(new Point(0, 0), new Move(1, Sign.X), new Point(0, 1), new Move(2, Sign.O)))
                                .build(),
                        Match.builder()
                                .p1(players.get(1))
                                .p2(players.get(0))
                                .status(MatchStatus.IN_PROGRESS)
                                .movesMap(Map
                                        .of(new Point(0, 0), new Move(1, Sign.X), new Point(0, 1), new Move(2, Sign.O)))
                                .build()));
        List<Match> matches = matchRepository.findAll();
        Assertions.assertThat(matches.size()).isEqualTo(2);
    }

}