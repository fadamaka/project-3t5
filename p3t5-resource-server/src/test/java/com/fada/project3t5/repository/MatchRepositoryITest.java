package com.fada.project3t5.repository;

import java.util.List;
import java.util.stream.StreamSupport;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fada.project3t5.domain.Match;

@SpringBootTest
class MatchRepositoryITest {

    List<Match> matchList = List.of(Match.builder().build());

    @Autowired
    MatchRepository matchRepository;

    @Test
    void test(){
        matchRepository.save(
                Match.builder()
                        .build());
        List<Match> matches = StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
        Assertions.assertThat(matches.size()).isEqualTo(1);
    }

}