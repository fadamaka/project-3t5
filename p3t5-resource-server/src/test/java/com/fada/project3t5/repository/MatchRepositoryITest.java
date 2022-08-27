package com.fada.project3t5.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import com.fada.project3t5.domain.Match;
import com.fada.project3t5.domain.MatchStatus;
import com.fada.project3t5.domain.Move;

@SpringBootTest
class MatchRepositoryITest {

   /* private static final int DYNAMODB_PORT = 8000;

    @Container
    public static GenericContainer dynamodb =
            new GenericContainer<>("amazon/dynamodb-local")
                    .withExposedPorts(DYNAMODB_PORT);*/

    List<Match> matchList = List.of(Match.builder().build());

    @Autowired
    MatchRepository matchRepository;

    @Test
    void test() {
        /*matchRepository.save(
                Match.builder()
                        .playerOne(1)
                        .playerTwo(2)
                        .status(MatchStatus.FINISHED)
                        .moves(Map.of(1, new Move("X", 1, 2), 2, new Move("Y", 2, 2)))
                        .build());*/
        List<Match> matches = StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
        Assertions.assertThat(matches.size()).isEqualTo(1);
    }

}